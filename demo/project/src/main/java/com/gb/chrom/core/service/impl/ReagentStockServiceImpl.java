package com.gb.chrom.core.service.impl;

import java.util.Calendar;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.transaction.interceptor.TransactionAspectSupport;

import com.gb.chrom.core.mapper.ReagentMapper;
import com.gb.chrom.core.mapper.ReagentStockInMapper;
import com.gb.chrom.core.mapper.ReagentStockOutMapper;
import com.gb.chrom.core.service.ReagentService;
import com.gb.chrom.core.service.ReagentStockService;
import com.gb.chrom.model.Reagent;
import com.gb.chrom.model.ReagentStockIn;
import com.gb.chrom.model.ReagentStockOut;
import com.gb.chrom.model.query.ReagentStockQuery;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;

/**
 * <p>
 * 
 * @author Summer
 * 
 *         Created by 2018年4月23日
 * @since
 */
@Service
public class ReagentStockServiceImpl implements ReagentStockService {

	private static Logger logger = LoggerFactory.getLogger(ReagentService.class);

	@Autowired
	private ReagentMapper reagentMapper;
	@Autowired
	private ReagentStockInMapper reagentStockInMapper;
	@Autowired
	private ReagentStockOutMapper reagentStockOutMapper;

	@Override
	@Transactional
	public boolean reagentPutInStorage(ReagentStockIn stockIn) {
		try {
			stockIn.setSurplusQuantity(stockIn.getQuantity());
			stockIn.setEntryTime(Calendar.getInstance().getTime());

			synchronized (reagentMapper) {
				Reagent reagent = reagentMapper.findReagentById(stockIn.getReagentId());
				int affect = reagentMapper.reagentPutInStorage(stockIn.getReagentId(), stockIn.getQuantity(), stockIn.getEntryTime(), reagent.getStocks());

				if (affect < 1) {
					throw new RuntimeException(" Put in storage failed.");
				}
				stockIn.setReagent(reagent.getName());
			}
			reagentStockInMapper.saveReagentStockIn(stockIn);
			return true;
		} catch (Exception e) {
			logger.error("Exception occurred during regent put in storage: ", e);
			TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
			throw new RuntimeException(e);
		}
	}

	@Override
	@Transactional
	public boolean reagentPutOutStorage(ReagentStockOut stockOut) {
		try {
			stockOut.setOutTime(Calendar.getInstance().getTime());

			synchronized (reagentMapper) {
				Reagent reagent = reagentMapper.findReagentById(stockOut.getReagentId());
				int affect = reagentMapper.reagentPutOutStorage(stockOut.getReagentId(), stockOut.getQuantity(), stockOut.getOutTime(), reagent.getStocks());
				if (affect < 1) {
					throw new RuntimeException(" Put out storage failed.");
				}
				
				long outQuantity = stockOut.getQuantity();
				List<ReagentStockIn> outList = reagentStockInMapper.findReagentAvailableStocksForList(stockOut.getReagentId());
				stockOut.setReagent(reagent.getName());

				for (ReagentStockIn out : outList) {
					if (out.getSurplusQuantity() >= outQuantity) {
						stockOut.setBatchNo(out.getBatchNo());
						stockOut.setQuantity(outQuantity);
						reagentStockOutMapper.saveReagentStockOut(stockOut);
						reagentStockInMapper.updateReagentStocks(out.getBatchNo(), out.getSurplusQuantity() - outQuantity);
						
						break;
					} else {
						outQuantity = outQuantity - out.getSurplusQuantity();
						stockOut.setBatchNo(out.getBatchNo());
						stockOut.setQuantity(out.getSurplusQuantity());
						reagentStockOutMapper.saveReagentStockOut(stockOut);
						reagentStockInMapper.updateReagentStocks(out.getBatchNo(), 0L);
					}
				}
			}

			return true;
		} catch (Exception e) {
			logger.error("Exception occurred during regent put out storage: ", e);
			TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
			throw new RuntimeException(e);
		}
	}

	@Override
	public PageInfo<ReagentStockIn> queryReagentStockInForPagingList(ReagentStockQuery query) {
		try {
			PageHelper.offsetPage(query.getOffset(), query.getLimit(), query.isCount());
			return reagentStockInMapper.findReagentStockInForList(query).toPageInfo();
		} catch (Exception e) {
			logger.error("Exception occurred during query regent stock in for paging list: ", e);
		}

		return new PageInfo<>();
	}

	@Override
	public PageInfo<ReagentStockOut> queryReagentStockOutForPagingList(ReagentStockQuery query) {
		try {
			PageHelper.offsetPage(query.getOffset(), query.getLimit(), query.isCount());
			return reagentStockOutMapper.findReagentStockOutForList(query).toPageInfo();
		} catch (Exception e) {
			logger.error("Exception occurred during query regent stock out for paging list: ", e);
		}

		return new PageInfo<>();
	}

}
