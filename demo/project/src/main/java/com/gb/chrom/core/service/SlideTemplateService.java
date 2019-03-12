package com.gb.chrom.core.service;

import com.gb.chrom.model.SlideTemplate;

/**
 * @since 1.0
 * @author Summer
 */
public interface SlideTemplateService {

	/**
	 * 查询载玻片模板
	 * 
	 * @return
	 */
	public SlideTemplate querySlideTemplate();

	/**
	 * 保存载玻片模板
	 * 
	 * @param template
	 * @return
	 */
	public boolean saveSlideTemplate(SlideTemplate template);

	/**
	 * 更新载玻片模板
	 * 
	 * @param template
	 * @return
	 */
	public boolean updateSlideTemplate(SlideTemplate template);

}
