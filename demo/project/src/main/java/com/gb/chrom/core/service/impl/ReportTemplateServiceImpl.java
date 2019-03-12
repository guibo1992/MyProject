package com.gb.chrom.core.service.impl;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.gb.chrom.core.MapperException;
import com.gb.chrom.core.mapper.ReportTemplateMapper;
import com.gb.chrom.core.service.ReportTemplateService;
import com.gb.chrom.model.ReportTemplate;
import com.gb.chrom.model.query.ReportTemplateQuery;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;

/**
 * @author Summer
 *
 *         2018年8月2日
 */
@Service
public class ReportTemplateServiceImpl implements ReportTemplateService {

	private static Logger logger = LoggerFactory.getLogger(ReportTemplateService.class);

	@Autowired
	private ReportTemplateMapper reportTemplateMapper;

	@Override
	public boolean addReportTemplate(ReportTemplate template) {
		try {
			reportTemplateMapper.saveReportTemplate(template);
			return true;
		} catch (MapperException e) {
			logger.error("Exception occurred during save report template :", e);
			throw new RuntimeException(e);
		}
	}

	@Override
	public boolean updateReportTemplate(ReportTemplate template) {
		try {
			int affect = reportTemplateMapper.updateReportTemplate(template);
			return affect > 0;
		} catch (MapperException e) {
			logger.error("Exception occurred during update report template :", e);
			throw new RuntimeException(e);
		}
	}

	@Override
	public boolean removeReportTemplate(long id) {
		try {
			ReportTemplate template = new ReportTemplate();
			template.setId(id);
			template.setStatus(false);
			int affect = reportTemplateMapper.updateReportTemplate(template);

			return affect > 0;
		} catch (MapperException e) {
			logger.error("Exception occurred during remove report template :", e);
			throw new RuntimeException(e);
		}
	}

	@Override
	public boolean undeleteReportTemplate(long id) {
		try {
			ReportTemplate template = new ReportTemplate();
			template.setId(id);
			template.setStatus(true);
			int affect = reportTemplateMapper.updateReportTemplate(template);

			return affect > 0;
		} catch (MapperException e) {
			logger.error("Exception occurred during undelete report template :", e);
			throw new RuntimeException(e);
		}
	}

	@Override
	public ReportTemplate queryReportTemplate(String name) {
		return null;
	}

	@Override
	public ReportTemplate queryReportTemplate(long id) {
		try {
			return reportTemplateMapper.findReportTemplateById(id);
		} catch (MapperException e) {
			logger.error("Exception occurred during query report template :", e);
			throw new RuntimeException(e);
		}
	}

	@Override
	public PageInfo<ReportTemplate> queryReportTemplateForPagingList(ReportTemplateQuery query) {
		try {
			PageHelper.offsetPage(query.getOffset(), query.getLimit());
			return reportTemplateMapper.findReportTemplateForList(query).toPageInfo();
		} catch (MapperException e) {
			logger.error("Exception occurred during query report template :", e);
		}

		return new PageInfo<>();
	}

}
