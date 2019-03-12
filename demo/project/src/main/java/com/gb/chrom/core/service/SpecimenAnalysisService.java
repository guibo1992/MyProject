package com.gb.chrom.core.service;

import com.gb.chrom.model.Specimen;
import com.gb.chrom.model.SpecimenAnalysisResult;
import com.gb.chrom.model.query.SpecimenQuery;
import com.github.pagehelper.PageInfo;

/**
 * @author Summer
 *
 *         2018年7月31日
 */
public interface SpecimenAnalysisService {

	/**
	 * 完成标本分析
	 * 
	 * @param result
	 * @return
	 */
	public boolean completeSpecimenAnalysis(SpecimenAnalysisResult result);

	/**
	 * 查询标本分析
	 * 
	 * @param query
	 * @return
	 */
	public PageInfo<Specimen> querySpecimenAnalysisForPagingList(SpecimenQuery query);

}
