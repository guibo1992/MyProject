package com.gb.chrom.core.mapper;

import org.apache.ibatis.annotations.Mapper;

import com.gb.chrom.core.MapperException;
import com.gb.chrom.model.SlideTemplate;

/**
 * @since 1.0
 * @author Summer
 */
@Mapper
public interface SlideTemplateMapper {

	/**
	 * 保存载玻片模板
	 * 
	 * @param template
	 * @return
	 * @throws MapperException
	 */
	Integer saveSlideTemplate(SlideTemplate template) throws MapperException;
	
	/**
	 * 更新载玻片模板
	 * 
	 * @param tmpl
	 * @return
	 * @throws MapperException
	 */
	Integer updateSlideTemplate(SlideTemplate template) throws MapperException;

	/**
	 * 查询载玻片模板
	 * 
	 * @return
	 * @throws MapperException
	 */
	SlideTemplate findSlideTemplate() throws MapperException;

}
