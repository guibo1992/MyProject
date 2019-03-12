package com.gb.chrom.web.controller;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.subject.Subject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.gb.chrom.config.cache.UnreadNoticeCache;
import com.gb.chrom.core.service.MessageService;
import com.gb.chrom.model.Message;
import com.gb.chrom.model.User;
import com.gb.chrom.model.query.MessageQuery;
import com.gb.chrom.web.result.JsonResult;
import com.gb.chrom.web.result.PaginatorJsonResult;

/**
 * <p>
 * 
 * @author Danson
 * 
 *         Created by 2018年5月13日
 * @since
 */
@Controller
@RequestMapping(value = "/message")
public class MessageController {

	@Autowired
	private MessageService messageService;

	@Autowired
	private UnreadNoticeCache unreadNoticeCache;

	@RequestMapping(value = "/list.html")
	public String messagePage() {
		return "view/message-list";
	}

	@RequestMapping(value = "/list")
	public @ResponseBody PaginatorJsonResult message(MessageQuery query) {
		return PaginatorJsonResult.getPaginatorResult(messageService.queryMessageForPagingList(query));
	}

	@RequestMapping(value = "/add.html")
	public String addMessage() {
		return "view/message-edit";
	}

	@RequestMapping(value = "/release")
	public @ResponseBody JsonResult releaseMessage(Message message, BindingResult result) {
		if (result.hasErrors()) {
			return JsonResult.error("错误的参数提交!");
		}

		Subject subject = SecurityUtils.getSubject();
		User user = (User) subject.getSession().getAttribute(User._ACTIVE_USER_JSESSIONID);
		message.setReleaserId(user.getId());
		return JsonResult.getJsonResult(messageService.releaseMessage(message), "公告发布出错!");
	}

	@RequestMapping(value = "/{id}/update")
	public @ResponseBody JsonResult messageUpdate(@PathVariable("id") long id, Message message, BindingResult result) {
		if (result.hasErrors()) {
			return JsonResult.error("错误的参数提交!");
		}
		message.setId(id);
		return JsonResult.getJsonResult(messageService.updateMessage(message));
	}

	@RequestMapping(value = "/{id}.html")
	public String queryMessageById(@PathVariable("id") long id, Model model) {
		model.addAttribute("message", messageService.queryMessage(id));
		return "view/message-edit";
	}

	@RequestMapping(value = "/{id}/remove")
	public @ResponseBody JsonResult removeMessage(@PathVariable("id") int id) {
		return JsonResult.getJsonResult(messageService.deleteMessage(id));
	}

	@RequestMapping(value = "/all.html")
	public String allMessage() {
		return "view/message-all";
	}

	@RequestMapping(value = "/{id}/view.html")
	public String viewMessage(@PathVariable("id") long id, Model model) {
		Subject subject = SecurityUtils.getSubject();
		User user = (User) subject.getSession().getAttribute(User._ACTIVE_USER_JSESSIONID);
		unreadNoticeCache.getUnreadNotices(user.getUsername(), id);
		model.addAttribute("message", messageService.queryMessage(id));

		return "view/message";
	}

}
