package com.gb.chrom.core.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.gb.chrom.core.MapperException;
import com.gb.chrom.model.MessageAttachment;

/**
 * @author Summer
 *
 *         2018年8月2日
 */
@Mapper
public interface MessageAttachmentMapper {

	/**
	 * 保存消息附件
	 * 
	 * @param attachList
	 * @return
	 * @throws MapperException
	 */
	Integer saveMessageAttachmentList(List<MessageAttachment> attachList) throws MapperException;

	/**
	 * 删除消息附件
	 * 
	 * @param messageId
	 * @return
	 * @throws MapperException
	 */
	Integer deleteMessageAttachmentsByMeeageId(Long messageId) throws MapperException;

	/**
	 * 查询消息附件
	 * 
	 * @param id
	 * @return
	 */
	List<MessageAttachment> findMessageAttachmentByMessageId(Long id) throws MapperException;

}
