package com.gb.chrom.core.mapper;

import org.apache.ibatis.annotations.Mapper;

import com.gb.chrom.core.MapperException;
import com.gb.chrom.model.TechnicalDocument;
import com.gb.chrom.model.query.TechnicalDocumentQuery;
import com.github.pagehelper.Page;

/**
 * @since 1.0
 * @author Summer
 */
@Mapper
public interface TechnicalDocumentMapper {

	/**
	 * 保存技术文档
	 * 
	 * @param document
	 * @return
	 * @throws MapperException
	 */
	Integer saveTechnicalDocument(TechnicalDocument document) throws MapperException;

	/**
	 * 删除技术文档
	 * 
	 * @param id
	 * @return
	 * @throws MapperException
	 */
	Integer deleteTechnicalDocumentById(Long id) throws MapperException;

	/**
	 * 查询技术文档列表
	 * 
	 * @param query
	 * @return
	 * @throws MapperException
	 */
	Page<TechnicalDocument> findTechnicalDocumentForList(TechnicalDocumentQuery query) throws MapperException;

}
