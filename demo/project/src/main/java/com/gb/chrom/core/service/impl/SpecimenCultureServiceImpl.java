package com.gb.chrom.core.service.impl;

import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.transaction.interceptor.TransactionAspectSupport;

import com.gb.chrom.constants.SpecimenStatus;
import com.gb.chrom.core.MapperException;
import com.gb.chrom.core.mapper.SpecimenCultureMapper;
import com.gb.chrom.core.mapper.SpecimenMapper;
import com.gb.chrom.core.service.SpecimenCultureService;
import com.gb.chrom.model.Specimen;
import com.gb.chrom.model.SpecimenCulture;
import com.gb.chrom.model.query.SpecimenQuery;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;

/**
 * @author Summer
 *
 *         2018年7月29日
 */
@Service
public class SpecimenCultureServiceImpl implements SpecimenCultureService {

	private static Logger logger = LoggerFactory.getLogger(SpecimenCultureService.class);

	@Autowired
	private SpecimenCultureMapper specimenCultureMapper;
	@Autowired
	private SpecimenMapper specimenMapper;

	@Transactional
	@Override
	public boolean addSpecimenCultureProcess(SpecimenCulture culture) {
		try {
			specimenCultureMapper.saveSpecimenCulture(culture);

			Specimen specimen = new Specimen();
			specimen.setId(culture.getSpecimenId());
			// specimen.setSpecimenStatus(culture.getProcessOrder());
			specimen.setStatus(true);

//			if (SpecimenStatus.STATUS_OF_CULTURE_COMPLETED == culture.getProcessOrder()) {
//				culture.setCompletedStatus(true);
//			}

			if (culture.getCompletedStatus()) {
				specimen.setCultureStatus(true);
				specimen.setCultureCompletedTime(culture.getProcessTime());
			}
			if (specimenMapper.updateSpecimen(specimen) < 1) {
				throw new RuntimeException("Update specimen culture status failed.");
			}
			return true;
		} catch (MapperException e) {
			logger.error("Exception occurred during culture specimen process :", e);
			TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
			throw new RuntimeException(e);
		}
	}

	@Transactional
	@Override
	public boolean processSpecimenListProcess(SpecimenCulture specimenCulture, String... specimenNos) {
		List<SpecimenCulture> cultureList = new ArrayList<>();
		Specimen specimen = null;
		SpecimenCulture culture = null;

		try {
			for (String num : specimenNos) {
				specimen = specimenMapper.findSpecimenBySpecimenNo(num);

				culture = new SpecimenCulture();
				culture.setSpecimenId(specimen.getId());
				culture.setHandlerId(specimenCulture.getHandlerId());
				culture.setProcessOrder(specimenCulture.getProcessOrder());
				culture.setProcessStep(specimenCulture.getProcessStep());
				culture.setProcessTime(specimenCulture.getProcessTime());
				culture.setCompletedStatus(specimenCulture.getCompletedStatus());
				culture.setRemark(specimenCulture.getRemark());
				cultureList.add(culture);

				specimen.setStatus(true);
				// specimen.setSpecimenStatus(culture.getProcessOrder());

				if (culture.getCompletedStatus()) {
					specimen.setCultureStatus(true);
					specimen.setCultureCompletedTime(culture.getProcessTime());
				}
				if (specimenMapper.updateSpecimen(specimen) < 1) {
					throw new RuntimeException("Update specimen culture status failed.");
				}
			}
			specimenCultureMapper.batchSaveSpecimensCultureList(cultureList);

			return true;
		} catch (Exception e) {
			logger.error("Exception occurred during batch process culture specimen list :", e);
			TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
			throw new RuntimeException(e);
		}
	}

	@Override
	public Specimen querySpecimenCulture(long id) {
		try {
			return specimenCultureMapper.findCultureSpecimenById(id);
		} catch (MapperException e) {
			logger.error("Exception occurred during query culture's specimen info :", e);
			throw new RuntimeException(e);
		}
	}

	@Override
	public PageInfo<Specimen> queryCultureSpecimenForPagingList(SpecimenQuery query) {
		try {
			PageHelper.offsetPage(query.getOffset(), query.getLimit());
			return specimenCultureMapper.findCultureSpecimenForList(query).toPageInfo();
		} catch (MapperException e) {
			logger.error("Exception occurred during query culture specimen for paging list :", e);
		}
		return new PageInfo<>();
	}

	@Override
	public List<String> queryCultureSpecimenNoForList(int typeId, int index) {
		try {
			if (index < SpecimenStatus.STATUS_OF_CULTURE) {
				index = SpecimenStatus.STATUS_OF_CULTURE;
			}
			if (index > SpecimenStatus.STATUS_OF_CULTURE_SCAN) {
				index = SpecimenStatus.STATUS_OF_CULTURE_SCAN;
			}

			return specimenCultureMapper.findCultureSpecimenNoForList(typeId, --index);
		} catch (MapperException e) {
			logger.error("Exception occurred during query next culture specimen No. for list :", e);
			throw new RuntimeException(e);
		}
	}

}
