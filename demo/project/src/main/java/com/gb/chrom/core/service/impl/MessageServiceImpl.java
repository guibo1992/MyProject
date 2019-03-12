package com.gb.chrom.core.service.impl;

import java.util.Calendar;
import java.util.List;

import org.apache.commons.collections.CollectionUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.transaction.interceptor.TransactionAspectSupport;

import com.gb.chrom.config.cache.UnreadNoticeCache;
import com.gb.chrom.core.MapperException;
import com.gb.chrom.core.mapper.MessageAttachmentMapper;
import com.gb.chrom.core.mapper.MessageMapper;
import com.gb.chrom.core.mapper.UserMapper;
import com.gb.chrom.core.service.MessageService;
import com.gb.chrom.model.Message;
import com.gb.chrom.model.User;
import com.gb.chrom.model.query.MessageQuery;
import com.gb.chrom.model.query.UserQuery;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;

/**
 * @since 1.0
 * @author Summer
 */
@Service
public class MessageServiceImpl implements MessageService {

	private static final Logger logger = LoggerFactory.getLogger(MessageServiceImpl.class);

	@Autowired
	private MessageMapper messageMapper;
	@Autowired
	private MessageAttachmentMapper messageAttachmentMapper;

	@Autowired
	private UnreadNoticeCache unreadNoticeCache;

	@Autowired
	private UserMapper userMapper;

	@Transactional
	@Override
	public boolean releaseMessage(Message message) {
		try {
			message.setReleaseDate(Calendar.getInstance().getTime());
			messageMapper.saveMessage(message);

			if (CollectionUtils.isNotEmpty(message.getAttachList())) {
				message.getAttachList().forEach(attach -> {
					attach.setMessageId(message.getId());
				});
				messageAttachmentMapper.saveMessageAttachmentList(message.getAttachList());
			}

			UserQuery query = new UserQuery();
			query.setCount(false);
			query.setLimit(Integer.MAX_VALUE);
			List<User> userList = userMapper.findUserForList(query);

			for (User user : userList) {
				//if (!user.getId().equals(message.getReleaserId())) {
					unreadNoticeCache.putUnreadNotices(user.getUsername(), message);
				//}
			}

			return true;
		} catch (MapperException e) {
			logger.error("Exception occurred during save message :", e);
			TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
			throw new RuntimeException(e);
		}
	}

	@Transactional
	@Override
	public boolean updateMessage(Message message) {
		try {
			messageMapper.updateMessage(message);
			messageAttachmentMapper.deleteMessageAttachmentsByMeeageId(message.getId());

			if (CollectionUtils.isNotEmpty(message.getAttachList())) {
				message.getAttachList().forEach(attach -> {
					attach.setMessageId(message.getId());
				});
				messageAttachmentMapper.saveMessageAttachmentList(message.getAttachList());
			}
			
			UserQuery query = new UserQuery();
			query.setCount(false);
			query.setLimit(Integer.MAX_VALUE);
			List<User> userList = userMapper.findUserForList(query);

			for (User user : userList) {
				//if (!user.getId().equals(message.getReleaserId())) {
					unreadNoticeCache.updateUnreadNotices(user.getUsername(), message);
				//}
			}

			return true;
		} catch (MapperException e) {
			logger.error("Exception occurred during update messaget :", e);
			TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
			throw new RuntimeException(e);
		}
	}

	//删除邮件
	@Transactional
	@Override
	public boolean deleteMessage(long id) {
		try {
			messageMapper.deleteMessageById(id);
			messageAttachmentMapper.deleteMessageAttachmentsByMeeageId(id);

			return true;
		} catch (MapperException e) {
			logger.error("Exception occurred during delete message :", e);
			TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
			throw new RuntimeException(e);
		}
	}

	//单条查询邮件
	@Override
	public Message queryMessage(long id) {
		try {
			Message message = messageMapper.findMessageById(id);
			if (null != message) {
				message.setAttachList(messageAttachmentMapper.findMessageAttachmentByMessageId(id));
			}

			return message;
		} catch (MapperException e) {
			logger.error("Exception occurred during query message :", e);
		}
		return null;
	}

	//分页
	@Override
	public PageInfo<Message> queryMessageForPagingList(MessageQuery query) {
		try {
			PageHelper.offsetPage(query.getOffset(), query.getLimit());
			return messageMapper.findMessageForList(query).toPageInfo();
		} catch (MapperException e) {
			logger.error("Exception occurred during query message for paging list :", e);
		}
		return new PageInfo<>();
	}

}
