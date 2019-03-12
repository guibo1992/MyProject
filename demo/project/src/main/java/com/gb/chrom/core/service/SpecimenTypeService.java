package com.gb.chrom.core.service;

import java.util.List;

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
public interface SpecimenTypeService {

	/**
	 * 增加标本类型
	 * 
	 * @param type
	 * @return
	 */
	public boolean addSpecimenType(SpecimenType type);

	/**
	 * 更新标本类型
	 * 
	 * @param type
	 * @return
	 */
	public boolean updateSpecimenType(SpecimenType type);

	/**
	 * 删除标本类型
	 * 
	 * @param id
	 * @return
	 */
	public boolean removeSpecimenType(int id);

	/**
	 * 撤消删除标本类型
	 * 
	 * @param id
	 * @return
	 */
	public boolean undeleteSpecimenType(int id);

	/**
	 * 查询标本类型
	 * 
	 * @param id
	 * @return
	 */
	SpecimenType querySpecimenType(int id);

	/**
	 * 查询标本类型列表
	 * 
	 * @param query
	 * @return
	 */
	public List<SpecimenType> querySpecimenTypeForList(SpecimenTypeQuery query);

}
