package com.gb.chrom.core.mapper;

import org.apache.ibatis.annotations.Mapper;

import com.gb.chrom.core.MapperException;
import com.gb.chrom.model.Message;
import com.gb.chrom.model.query.MessageQuery;
import com.github.pagehelper.Page;

/**
 * @author Summer
 *
 * 		2018年8月2日
 */
@Mapper
public interface MessageMapper {
	
	/**
	 * 保存消息
	 * 
	 * @param message
	 * @return
	 * @throws MapperException
	 */
	Integer saveMessage(Message message) throws MapperException;

	/**
	 * 更新消息
	 * 
	 * @param message
	 * @return
	 * @throws MapperException
	 */
	Integer updateMessage(Message message) throws MapperException;

	/**
	 * 删除消息
	 * 
	 * @param id
	 * @return
	 * @throws MapperException
	 */
	Integer deleteMessageById(Long id) throws MapperException;

	/**
	 * 根据ID查询消息
	 * 
	 * @param id
	 * @return
	 * @throws MapperException
	 */
	Message findMessageById(Long id) throws MapperException;

	/**
	 * 查询消息列表
	 * 
	 * @param query
	 * @return
	 * @throws MapperException
	 */
	Page<Message> findMessageForList(MessageQuery query) throws MapperException;

}
