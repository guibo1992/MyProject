package com.gb.chrom.core.service.impl;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.gb.chrom.core.mapper.ClinicalInfoMapper;
import com.gb.chrom.core.service.ClinicalInfoService;
import com.gb.chrom.model.ClinicalInfo;


@Service
public class ClinicalInfoServiceImpl implements ClinicalInfoService {
	
	private static final Logger logger = LoggerFactory.getLogger(ClinicalInfoServiceImpl.class);
	
	@Autowired
	private ClinicalInfoMapper clinicalInfoMapper;
	
	@Override
	public List<ClinicalInfo> findClinicalInfo(char typehead) {
		try {
			List<ClinicalInfo> list=clinicalInfoMapper.findClinicalInfo(typehead);
			if(null !=list) {
				return list;
			}
		} catch (Exception e) {
			logger.error("Exception occurred during query clinicalInfo :", e);
		}
		return null;
	}

}
