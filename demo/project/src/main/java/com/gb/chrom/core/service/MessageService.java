package com.gb.chrom.core.service;

import com.gb.chrom.model.Message;
import com.gb.chrom.model.query.MessageQuery;
import com.github.pagehelper.PageInfo;

/**
 * @since 1.0
 * @author Summer
 */
public interface MessageService {

	/**
	 * 发布消息
	 * 
	 * @param message
	 * @return
	 */
	public boolean releaseMessage(Message message);

	/**
	 * 更新消息
	 * 
	 * @param message
	 * @return
	 */
	public boolean updateMessage(Message message);

	/**
	 * 删除消息
	 * 
	 * @param id
	 * @return
	 */
	public boolean deleteMessage(long id);

	/**
	 * 查询消息
	 * 
	 * @param id
	 * @return
	 */
	public Message queryMessage(long id);

	/**
	 * 分页查询消息
	 * 
	 * @return
	 */
	public PageInfo<Message> queryMessageForPagingList(MessageQuery query);

}
