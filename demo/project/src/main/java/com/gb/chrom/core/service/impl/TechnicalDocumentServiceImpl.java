package com.gb.chrom.core.service.impl;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.gb.chrom.core.MapperException;
import com.gb.chrom.core.mapper.TechnicalDocumentMapper;
import com.gb.chrom.core.service.TechnicalDocumentService;
import com.gb.chrom.model.TechnicalDocument;
import com.gb.chrom.model.query.TechnicalDocumentQuery;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;

/**
 * @since 1.0
 * @author Summer
 */
@Service
public class TechnicalDocumentServiceImpl implements TechnicalDocumentService {

	private static final Logger logger = LoggerFactory.getLogger(TechnicalDocumentService.class);

	@Autowired
	private TechnicalDocumentMapper documentMapper;

	@Override
	public boolean addTechnicalDocument(TechnicalDocument document) {
		try {
			documentMapper.saveTechnicalDocument(document);
			return true;
		} catch (MapperException e) {
			logger.error("Exception occurred during add technical document :", e);
		}
		return false;
	}

	@Override
	public boolean deleteTechnicalDocument(long id) {
		try {
			documentMapper.deleteTechnicalDocumentById(id);
			return true;
		} catch (MapperException e) {
			logger.error("Exception occurred during delete technical document :", e);
		}
		return false;
	}

	@Override
	public PageInfo<TechnicalDocument> queryTechnicalDocumentForPagingList(TechnicalDocumentQuery query) {
		try {
			PageHelper.offsetPage(query.getOffset(), query.getLimit(), query.isCount());
			return documentMapper.findTechnicalDocumentForList(query).toPageInfo();
		} catch (MapperException e) {
			logger.error("Exception occurred during query technical document for paging list :", e);
		}
		return new PageInfo<>();
	}

}
