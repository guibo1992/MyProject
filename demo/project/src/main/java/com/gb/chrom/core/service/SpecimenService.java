package com.gb.chrom.core.service;

import java.util.List;

import com.gb.chrom.model.Specimen;
import com.gb.chrom.model.User;
import com.gb.chrom.model.query.SpecimenQuery;
import com.github.pagehelper.PageInfo;

/**
 * @since 1.0
 * @author Summer
 */
public interface SpecimenService {

	/**
	 * 保存标本
	 * 
	 * @param specimen
	 * @param analystList
	 * @return
	 */
	public boolean addSpecimen(Specimen specimen, List<User> analystList);

	/**
	 * 更新标本
	 * 
	 * @param specimen
	 * @return
	 */
	public boolean updateSpecimen(Specimen specimen);

	/**
	 * 删除标本
	 * 
	 * @param id
	 * @return
	 */
	public boolean deleteSpecimen(long id);

	/**
	 * 撤消删除
	 * 
	 * @param id
	 * @return
	 */
	public boolean undeleteSpecimen(long id);

	/**
	 * 查询标本
	 * 
	 * @param id
	 * @return
	 */
	public Specimen querySpecimen(long id);

	/**
	 * 验证标本号是否正确
	 * 
	 * @param specimenNo
	 * @return
	 */
	public boolean validateSpecimenNo(String specimenNo);
	
	/**
	 * 查询标本
	 * 
	 * @param id
	 * @return
	 */
	public Specimen querySpecimen(String hisId);

	/**
	 * 分页查询标本
	 * 
	 * @return
	 */
	public PageInfo<Specimen> querySpecimenForPagingList(SpecimenQuery query);

}
