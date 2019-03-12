package com.gb.chrom.core.service.impl;

import java.util.Collections;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.gb.chrom.core.mapper.SpecimenTypeMapper;
import com.gb.chrom.core.service.SpecimenTypeService;
import com.gb.chrom.model.SpecimenType;
import com.gb.chrom.model.query.SpecimenTypeQuery;

/**
 * <p>
 * 
 * @author Summer
 * 
 *         Created by 2018年4月3日
 * @since
 */
@Service
public class SpecimenTypeServiceImpl implements SpecimenTypeService {

	private static Logger logger = LoggerFactory.getLogger(SpecimenTypeService.class);

	@Autowired
	private SpecimenTypeMapper specimenTypeMapper;

	@Override
	public boolean addSpecimenType(SpecimenType type) {
		try {
			type.setTypeHead(type.getTypeHead().toUpperCase());
			specimenTypeMapper.saveSpecimenType(type);
			return true;
		} catch (Exception e) {
			logger.error("Exception occurred during save speciment type: ", e);
		}

		return false;
	}

	@Override
	public boolean updateSpecimenType(SpecimenType type) {
		try {
			type.setTypeHead(type.getTypeHead().toUpperCase());
			int affect = specimenTypeMapper.updateSpecimenType(type);
			return affect > 0;
		} catch (Exception e) {
			logger.error("Exception occurred during update speciment type: ", e);
		}

		return false;
	}

	@Override
	public boolean removeSpecimenType(int id) {
		try {
			SpecimenType type = new SpecimenType();
			type.setId(id);
			type.setStatus(false);
			int affect = specimenTypeMapper.updateSpecimenType(type);

			return affect > 0;
		} catch (Exception e) {
			logger.error("Exception occurred during remove speciment type: ", e);
		}

		return false;
	}

	@Override
	public boolean undeleteSpecimenType(int id) {
		try {
			SpecimenType type = new SpecimenType();
			type.setId(id);
			type.setStatus(true);
			int affect = specimenTypeMapper.updateSpecimenType(type);

			return affect > 0;
		} catch (Exception e) {
			logger.error("Exception occurred during undelete speciment type: ", e);
		}

		return false;
	}

	@Override
	public SpecimenType querySpecimenType(int id) {
		try {
			return specimenTypeMapper.findSpecimenTypeById(id);
		} catch (Exception e) {
			logger.error("Exception occurred during find speciment type by id: ", e);
		}

		return null;
	}

	@Override
	public List<SpecimenType> querySpecimenTypeForList(SpecimenTypeQuery query) {
		try {
			return specimenTypeMapper.findSpecimenTypeForList(query);
		} catch (Exception e) {
			logger.error("Exception occurred during find speciment type for list: ", e);
		}

		return Collections.emptyList();
	}

}
