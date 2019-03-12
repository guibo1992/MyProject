package com.gb.chrom.core.service;

import com.gb.chrom.core.MapperException;
import com.gb.chrom.model.Reagent;
import com.gb.chrom.model.query.ReagentQuery;
import com.github.pagehelper.PageInfo;

/**
 * <p>
 * 
 * @author Summer
 * 
 *         Created by 2018年4月23日
 * @since
 */
public interface ReagentService {

	/**
	 * 添加试剂
	 * 
	 * @param reagent
	 * @return
	 */
	public boolean addReagent(Reagent reagent);

	/**
	 * 更新试剂
	 * 
	 * @param reagent
	 * @return
	 */
	public boolean updateReagent(Reagent reagent);

	/**
	 * 查询试剂
	 * 
	 * @param id
	 * @return
	 */
	public Reagent queryReagent(long id);

	/**
	 * 查询试剂列表
	 * 
	 * @param query
	 * @return
	 * @throws MapperException
	 */
	PageInfo<Reagent> queryReagentForPagingList(ReagentQuery query);

}
