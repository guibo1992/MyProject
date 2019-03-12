package com.gb.chrom.core.service.impl;

import java.util.Calendar;
import java.util.Date;

import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.time.DateUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.transaction.interceptor.TransactionAspectSupport;

import com.gb.chrom.core.MapperException;
import com.gb.chrom.core.mapper.SpecimenAnalysisMapper;
import com.gb.chrom.core.mapper.SpecimenMapper;
import com.gb.chrom.core.service.SpecimenAnalysisService;
import com.gb.chrom.model.Specimen;
import com.gb.chrom.model.SpecimenAnalysisResult;
import com.gb.chrom.model.query.SpecimenQuery;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;

/**
 * @author Summer
 *
 *         2018年7月31日
 */
@Service
public class SpecimenAnalysisServiceImpl implements SpecimenAnalysisService {

	private static Logger logger = LoggerFactory.getLogger(SpecimenAnalysisService.class);

	@Autowired
	private SpecimenMapper specimenMapper;
	@Autowired
	private SpecimenAnalysisMapper specimenAnalysisMapper;

	@Transactional
	@Override
	public boolean completeSpecimenAnalysis(SpecimenAnalysisResult result) {
		try {
			specimenAnalysisMapper.saveSpecimenAnalysisResult(result);

			Specimen specimen = specimenMapper.findSpecimenBySpecimenNo(result.getSpecimenNo());
			specimen.setAnalysisStatus(true);

			if (StringUtils.isNotBlank(result.getCompletedTime())) {
				Date date = DateUtils.parseDate(result.getCompletedTime().replaceAll("T|Z", " ").trim(), "yyyy-MM-DD HH:mm:ss");
				specimen.setAnalysisCompletedTime(date);
			} else {
				specimen.setAnalysisCompletedTime(Calendar.getInstance().getTime());
			}
			specimen.setAnalysisResult(result.getAnalysisResult());
			specimen.setAnalysisMetImg(result.getAnalysisMetImg());
			specimen.setAnalysisKarImg(result.getAnalysisKarImg());
			specimenMapper.updateSpecimen(specimen);

			return true;
		} catch (Exception e) {
			logger.error("Exception occurred during specimen analysis completed :", e);
			TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
			throw new RuntimeException(e);
		}
	}

	@Override
	public PageInfo<Specimen> querySpecimenAnalysisForPagingList(SpecimenQuery query) {
		try {
			PageHelper.offsetPage(query.getOffset(), query.getLimit());
			return specimenAnalysisMapper.findSpecimenAnalysisForList(query).toPageInfo();
		} catch (MapperException e) {
			logger.error("Exception occurred during query specimen analysis for paging list :", e);
		}
		return new PageInfo<>();
	}

}
