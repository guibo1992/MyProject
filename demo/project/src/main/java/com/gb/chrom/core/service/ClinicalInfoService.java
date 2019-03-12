package com.gb.chrom.core.service;

import java.util.List;

import com.gb.chrom.model.ClinicalInfo;

public interface ClinicalInfoService {
	
	/**
	 * 
	 */
	public List<ClinicalInfo> findClinicalInfo(char typehead);
}
