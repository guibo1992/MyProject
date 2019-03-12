package com.gb.chrom.core.service.impl;

import java.util.Calendar;

import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.LockedAccountException;
import org.apache.shiro.authc.UnknownAccountException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.gb.chrom.core.MapperException;
import com.gb.chrom.core.mapper.UserMapper;
import com.gb.chrom.core.service.LoginService;
import com.gb.chrom.model.User;

/**
 * @author Summer
 *
 *         2017年12月19日
 */
@Service
public class LoginServiceImpl implements LoginService<User> {

	private static final Logger logger = LoggerFactory.getLogger(LoginService.class);

	@Autowired
	private UserMapper userMapper;

	@Override
	public User login(String username) throws AuthenticationException {
		User user = null;
		try {
			user = userMapper.findUserByUsername(username);
		} catch (Exception e) {
			logger.error("Exception occurred during user login :", e);
		}

		if (null == user) {
			throw new UnknownAccountException("username does not exist.");
		}

		if (!user.getStatus()) {
			throw new LockedAccountException("user has been locked.");
		}

		logger.info("user login success!");
		try {
			user.setLoginTime(Calendar.getInstance().getTime());
			userMapper.updateUser(user);
		} catch (MapperException e) {
			logger.error("Exception occurred during user login after update :", e);
		}

		return user;
	}

	@Override
	public void afterLogin(long userId) {
		// try {
		// User user = new User();
		// user.setId(userId);
		// user.setLoginTime(Calendar.getInstance().getTime());
		// userMapper.updateUser(user);
		// } catch (DaoException e) {
		// logger.error("Exception occurred during user login after update :", e);
		// }
	}

}
