package com.gb.chrom.core.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.gb.chrom.core.MapperException;
import com.gb.chrom.model.SlideTemplateItem;

/**
 * @since 1.0
 * @author Summer
 */
@Mapper
public interface SlideTemplateItemMapper {

	/**
	 * 保存载玻片模板条目
	 * 
	 * @param itemList
	 * @return
	 * @throws MapperException
	 */
	Integer saveSlideTemplateItemList(List<SlideTemplateItem> itemList) throws MapperException;

	/**
	 * 删除载玻片模板条目
	 * 
	 * @param tmplId
	 * @return
	 * @throws MapperException
	 */
	Integer deleteSlideTemplateItemByTemplateId(Long tmplId) throws MapperException;

	/**
	 * 查询模板条目
	 * 
	 * @param id
	 * @return
	 * @throws MapperException
	 */
	List<SlideTemplateItem> findSlideTemplateItemForList(Long id) throws MapperException;

}
