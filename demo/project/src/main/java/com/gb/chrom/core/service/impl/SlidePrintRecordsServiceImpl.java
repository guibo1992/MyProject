package com.gb.chrom.core.service.impl;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.interceptor.TransactionAspectSupport;

import com.gb.chrom.core.MapperException;
import com.gb.chrom.core.mapper.SlidePrintRecordsMapper;
import com.gb.chrom.core.service.SlidePrintRecordsService;
import com.gb.chrom.model.SlidePrintRecords;
import com.gb.chrom.model.query.SlidePrintRecordsQuery;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;

/**
 * @since 1.0
 * @author Summer
 */
@Service
public class SlidePrintRecordsServiceImpl implements SlidePrintRecordsService {

	private static final Logger logger = LoggerFactory.getLogger(SlidePrintRecordsService.class);

	@Autowired
	private SlidePrintRecordsMapper specimenPrintRecordsMapper;

	@Override
	public boolean addSlidePrintRecords(SlidePrintRecords printLog) {
		try {
			// SpecimenPrintLog log = specimenPrintLogMapper.findSpecimenPrintLongBySpecimenId(printLog.getSpecimenId());
			// if (null != log) {
			// printLog.setId(log.getId());
			// specimenPrintLogMapper.updateSpecimenPrintLog(printLog);
			// } else {
			// specimenPrintLogMapper.saveSpecimenPrintLog(printLog);
			// }

			specimenPrintRecordsMapper.saveSlidePrintRecords(printLog);

			return true;
		} catch (MapperException e) {
			logger.error("Exception occurred during save slide print log :", e);
			TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
			throw new RuntimeException(e);
		}
	}

	@Override
	public PageInfo<SlidePrintRecords> querySlidePrintRecordsForPagingList(SlidePrintRecordsQuery query) {
		try {
			PageHelper.offsetPage(query.getOffset(), query.getLimit());
			return specimenPrintRecordsMapper.findSlidePrintRecordsForList(query).toPageInfo();
		} catch (MapperException e) {
			logger.error("Exception occurred during query slide print log for paging list :", e);
		}
		return new PageInfo<>();
	}

}
