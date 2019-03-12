package com.gb.chrom.core.service;

import com.gb.chrom.model.TechnicalDocument;
import com.gb.chrom.model.query.TechnicalDocumentQuery;
import com.github.pagehelper.PageInfo;

/**
 * @since 1.0
 * @author Summer
 */
public interface TechnicalDocumentService {

	/**
	 * 保存技术文档
	 * 
	 * @param document
	 * @return
	 */
	public boolean addTechnicalDocument(TechnicalDocument document);

	/**
	 * 删除技术文档
	 * 
	 * @param id
	 * @return
	 */
	public boolean deleteTechnicalDocument(long id);

	/**
	 * 分页查询技术文档
	 * 
	 * @return
	 */
	public PageInfo<TechnicalDocument> queryTechnicalDocumentForPagingList(TechnicalDocumentQuery query);

}
