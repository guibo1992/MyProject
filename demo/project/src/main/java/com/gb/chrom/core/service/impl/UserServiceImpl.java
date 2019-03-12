package com.gb.chrom.core.service.impl;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.gb.chrom.core.MapperException;
import com.gb.chrom.core.mapper.UserMapper;
import com.gb.chrom.core.service.UserService;
import com.gb.chrom.model.User;
import com.gb.chrom.model.query.UserQuery;
import com.gb.chrom.shiro.PasswordHash;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;

/**
 * @author Summer
 *
 *         2017年2月22日
 */
@Service
public class UserServiceImpl implements UserService {

	private static final Logger logger = LoggerFactory.getLogger(UserService.class);

	@Autowired
	private UserMapper userMapper;

	@Override
	public boolean addUser(User user) {
		try {
			PasswordHash hash = new PasswordHash();
			String hashed = hash.encryptToHex("123456", user.getUsername());
			user.setPassword(hashed);
			user.setSalt(hash.getCredentialsSalt());
			userMapper.saveUser(user);
			return true;
		} catch (MapperException e) {
			logger.error("Exception occurred during save user :", e);
		}

		return false;
	}

	@Override
	public boolean updateUser(User user) {
		try {
			if (StringUtils.isNotBlank(user.getPassword())) {
				PasswordHash hash = new PasswordHash();
				String hashed = hash.encryptToHex(user.getPassword(), user.getUsername());
				user.setPassword(hashed);
				user.setSalt(hash.getCredentialsSalt());
				
			}

			return userMapper.updateUser(user) > 0;
		} catch (MapperException e) {
			logger.error("Exception occurred during update user :", e);
		}

		return false;
	}

	@Override
	public boolean updatePassword(long userId, String password) {
		try {
			User user = userMapper.findUserById(userId);
			PasswordHash hash = new PasswordHash();
			String hashed = hash.encryptToHex(password, user.getUsername());
			user.setPassword(hashed);
			user.setSalt(hash.getCredentialsSalt());
			userMapper.updateUser(user);			
			return true;
		} catch (MapperException e) {
			logger.error("Exception occurred during update user login password :", e);
		}

		return false;
	}

	@Override
	public boolean verifyUserPassword(long userId, String password) {
		try {
			User user = userMapper.findUserById(userId);
			PasswordHash hash = new PasswordHash();
			hash.setCredentialsSalt(user.getSalt());
			String hashed = hash.encryptToHex(password, user.getUsername());
			return StringUtils.equals(user.getPassword(), hashed);
		} catch (MapperException e) {
			logger.error("Exception occurred during verify user login password :", e);
		}

		return false;
	}

	@Override
	public User queryUser(long userId) {
		try {
			return userMapper.findUserById(userId);
		} catch (MapperException e) {
			logger.error("Exception occurred during query user by id :", e);
		}
		return null;
	}

	@Override
	public User queryUser(String username) {
		try {
			return userMapper.findUserByUsername(username);
		} catch (MapperException e) {
			logger.error("Exception occurred during query user by username :", e);
		}
		return null;
	}

	@Override
	public PageInfo<User> queryUserForPagingList(UserQuery query) {
		try {
			PageHelper.offsetPage(query.getOffset(), query.getLimit());
			return userMapper.findUserForList(query).toPageInfo();
		} catch (MapperException e) {
			logger.error("Exception occurred during query user for paging list :", e);
		}
		return new PageInfo<>();
	}

	@Override
	public int queryUserTotalCount() {
		try {
			return userMapper.findUserTotalCount();
		} catch (MapperException e) {
			logger.error("Exception occurred during query user total count :", e);
			throw new RuntimeException(e);
		}
	}

}
