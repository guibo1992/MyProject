package com.gb.chrom.core.service.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.shiro.util.CollectionUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.transaction.interceptor.TransactionAspectSupport;

import com.gb.chrom.core.MapperException;
import com.gb.chrom.core.mapper.SlideTemplateItemMapper;
import com.gb.chrom.core.mapper.SlideTemplateMapper;
import com.gb.chrom.core.service.SlideTemplateService;
import com.gb.chrom.model.SlideTemplate;
import com.gb.chrom.model.SlideTemplateItem;

/**
 * @since 1.0
 * @author Summer
 */
@Service
public class SlideTemplateServiceImpl implements SlideTemplateService {

	private static final Logger logger = LoggerFactory.getLogger(SlideTemplateService.class);

	@Autowired
	private SlideTemplateMapper slideTemplateMapper;
	@Autowired
	private SlideTemplateItemMapper slideTemplateItemMapper;

	@Override
	public SlideTemplate querySlideTemplate() {
		try {
			SlideTemplate template = slideTemplateMapper.findSlideTemplate();
			if (null != template) {
				Map<String, SlideTemplateItem> itemMap = new HashMap<>();
				List<SlideTemplateItem> list = slideTemplateItemMapper.findSlideTemplateItemForList(template.getId());
				template.setItemList(list);

				list.forEach(item -> {
					itemMap.put(item.getType(), item);
				});
				template.setItemMap(itemMap);
			}

			return template;
		} catch (MapperException e) {
			logger.error("Exception occurred during query slide template :", e);
		}

		return null;
	}

	@Transactional
	@Override
	public boolean saveSlideTemplate(SlideTemplate template) {
		try {
			if (CollectionUtils.isEmpty(template.getItemList())) {
				return false;
			}
			slideTemplateMapper.saveSlideTemplate(template);
			template.getItemList().forEach(item -> item.setTmplId(template.getId()));
			slideTemplateItemMapper.saveSlideTemplateItemList(template.getItemList());

			return true;
		} catch (MapperException e) {
			logger.error("Exception occurred during save slide template :", e);
			TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
			throw new RuntimeException(e);
		}
	}

	@Transactional
	@Override
	public boolean updateSlideTemplate(SlideTemplate template) {
		try {
			slideTemplateMapper.updateSlideTemplate(template);
			slideTemplateItemMapper.deleteSlideTemplateItemByTemplateId(template.getId());

			template.getItemList().forEach(item -> item.setTmplId(template.getId()));
			slideTemplateItemMapper.saveSlideTemplateItemList(template.getItemList());

			return true;
		} catch (MapperException e) {
			logger.error("Exception occurred during save slide template :", e);
			TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
			throw new RuntimeException(e);
		}
	}

}
