package com.gb.chrom.config.cache;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.Cache;
import org.springframework.cache.CacheManager;
import org.springframework.stereotype.Component;

import com.gb.chrom.model.Message;

/**
 * <p>
 * 
 * @author Summer
 * 
 *         Created by 2018年4月23日
 * @since
 */
@Component
@SuppressWarnings("unchecked")
public class UnreadNoticeCache {

	private static Logger logger = LoggerFactory.getLogger(UnreadNoticeCache.class);

	private static final String UNREAD_NOTICE_CACHE_NAME = "unread-notice-cache";

	@Autowired
	private CacheManager cacheCacheManager;

	private Cache cache;

	@PostConstruct
	public void init() {
		cache = cacheCacheManager.getCache(UNREAD_NOTICE_CACHE_NAME);
		cache.clear();
		logger.info("Unread notices cache init completed.");
	}

	public void setUnreadNotices(String username, HttpServletRequest request) {
		List<Message> list = cache.get(username, List.class);
		if (null == list) {
			list = new ArrayList<>();
		}
		request.setAttribute("messageList", list);
	}

	public void putUnreadNotices(String username, Message message) {
		List<Message> list = cache.get(username, List.class);

		if (null == list) {
			list = new ArrayList<>();
		}
		list.add(message);
		cache.put(username, list);
	}
	
	public void updateUnreadNotices(String username, Message message) {
		List<Message> list = cache.get(username, List.class);

		if (null == list) {
			list = new ArrayList<>();
		}
		Iterator<Message> it = list.iterator();
		while (it.hasNext()) {
			Message msg = it.next();
			if (msg.getId().equals(message.getId())) {
				msg.setTitle(message.getTitle());
				msg.setBrief(message.getBrief());
				msg.setContent(message.getContent());
			}
		}
		cache.put(username, list);
	}

	public Message getUnreadNotices(String username, long msgId) {
		Message message = null;
		List<Message> list = cache.get(username, List.class);

		if (null == list) {
			list = new ArrayList<>();
		}
		Iterator<Message> it = list.iterator();
		while (it.hasNext()) {
			Message msg = it.next();
			if (msgId == msg.getId()) {
				message = msg;
				it.remove();
			}
		}
		cache.put(username, list);

		return message;
	}

}
