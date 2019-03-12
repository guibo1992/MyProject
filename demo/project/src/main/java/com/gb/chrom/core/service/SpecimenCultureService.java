package com.gb.chrom.core.service;

import java.util.List;

import com.gb.chrom.model.Specimen;
import com.gb.chrom.model.SpecimenCulture;
import com.gb.chrom.model.query.SpecimenQuery;
import com.github.pagehelper.PageInfo;

/**
 * @author Summer
 *
 *         2018年7月29日
 */
public interface SpecimenCultureService {

	/**
	 * Add 标本培养过程
	 * 
	 * @param culture
	 * @return
	 */
	public boolean addSpecimenCultureProcess(SpecimenCulture culture);

	/**
	 * 批量处理标本培养
	 * 
	 * @param culture
	 * @param specimenNos
	 * @return
	 */
	public boolean processSpecimenListProcess(SpecimenCulture culture, String... specimenNos);

	/**
	 * 查询标本培养信息
	 * 
	 * @param id
	 * @return
	 */
	public Specimen querySpecimenCulture(long id);

	/**
	 * 查询培养标本列表
	 * 
	 * @param query
	 * @return
	 */
	public PageInfo<Specimen> queryCultureSpecimenForPagingList(SpecimenQuery query);

	/**
	 * 查询待下一步处理的标本编号
	 * 
	 * @param typeId
	 * @param index
	 * @return
	 */
	public List<String> queryCultureSpecimenNoForList(int typeId, int index);

}
