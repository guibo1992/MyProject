package com.gb.chrom.web.controller;

import java.util.Optional;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

import org.apache.commons.lang3.StringUtils;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authz.annotation.RequiresRoles;
import org.apache.shiro.subject.Subject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.gb.chrom.core.service.UserService;
import com.gb.chrom.model.User;
import com.gb.chrom.model.query.UserQuery;
import com.gb.chrom.web.result.JsonResult;
import com.gb.chrom.web.result.PaginatorJsonResult;

@Controller
@RequestMapping(value = "/user")
public class UserController {

	@Autowired
	private UserService userService;

	@RequestMapping(value = "/profile")
	public String profile(HttpServletRequest request, Model model) {
		return "view/profile";
	}

	@RequestMapping("/update")
	public @ResponseBody JsonResult update(@Valid User user, BindingResult result) {
		if (result.hasErrors()) {
			return JsonResult.error("错误的参数提交!");
		}
		Subject subject = SecurityUtils.getSubject();
		User currUser = (User) subject.getSession().getAttribute(User._ACTIVE_USER_JSESSIONID);
		user.setId(currUser.getId());

		if (!StringUtils.equals(currUser.getUsername(), user.getUsername())) {
			Optional<User> optional = Optional.ofNullable(userService.queryUser(user.getUsername()));
			if (optional.isPresent() && !currUser.getId().equals(optional.get().getId())) {
				return JsonResult.error("用户名已经存!");
			}
		}
		boolean flag = userService.updateUser(user);
		if (flag) {
			subject.getSession().setAttribute(User._ACTIVE_USER_JSESSIONID, user);
			return JsonResult.success();
		}
		return JsonResult.error("用户资料更新失败!");
	}

	@RequestMapping("/password/update")
	public @ResponseBody JsonResult updatePassword(String origPassword, String password) {
		Subject subject = SecurityUtils.getSubject();
		User user = (User) subject.getSession().getAttribute(User._ACTIVE_USER_JSESSIONID);
		boolean result = userService.verifyUserPassword(user.getId(), origPassword);
		if (!result) {
			return JsonResult.error("原密码错误!");
		}

		return JsonResult.getJsonResult(userService.updatePassword(user.getId(), password), "密码更新失败!");
	}

	@RequiresRoles("administrator")
	@RequestMapping(value = "/list.html")
	public String users(HttpServletRequest request) {
		return "view/user";
	}

	@RequiresRoles("administrator")
	@RequestMapping(value = "/list")
	public @ResponseBody PaginatorJsonResult userList(UserQuery query) {
		return PaginatorJsonResult.getPaginatorResult(userService.queryUserForPagingList(query));
	}

	@RequiresRoles("administrator")
	@RequestMapping(value = "/{id}/profile", method = RequestMethod.GET)
	public @ResponseBody JsonResult getNavigation(@PathVariable("id") long id) {
		return JsonResult.success(userService.queryUser(id));
	}

	@RequiresRoles("administrator")
	@RequestMapping(value = "/add", method = RequestMethod.POST)
	public @ResponseBody JsonResult createUser(@Valid User user, BindingResult result) {
		if (result.hasErrors()) {
			return JsonResult.error("错误的参数提交!");
		}

		Optional<User> optional = Optional.ofNullable(userService.queryUser(user.getUsername()));
		if (optional.isPresent()) {
			return JsonResult.error("帐号已经存在!");
		}

		return JsonResult.getJsonResult(userService.addUser(user), "操作失败!");
	}

	@RequiresRoles("administrator")
	@RequestMapping("/{id}/update")
	public @ResponseBody JsonResult updateUser(@PathVariable("id") long id, @Valid User user, BindingResult result) {
		if (result.hasErrors()) {
			return JsonResult.error("错误的参数提交!");
		}
		user.setId(id);
		User existUser = userService.queryUser(user.getUsername());
		if (null != existUser && id != existUser.getId().longValue()) {
			return JsonResult.error("用户名已经存!");
		}

		return JsonResult.getJsonResult(userService.updateUser(user), "更新失败!");
	}

	@RequestMapping("/total/ceiling")
	public @ResponseBody JsonResult getUserTotal() {
		int total = userService.queryUserTotalCount();

		if (total > 9) {
			return JsonResult.error("用户数量已达上限, 请联系软件供应商!");
		}

		return JsonResult.success();
	}

}
