package com.gb.chrom.core.service;

import com.gb.chrom.model.User;
import com.gb.chrom.model.query.UserQuery;
import com.github.pagehelper.PageInfo;

/**
 * @author Summer
 *
 * @date 2016年3月4日
 */
public interface UserService {

	/**
	 * 添加用户
	 * 
	 * @param user
	 * @return
	 */
	public boolean addUser(User user);

	/**
	 * 更新用户
	 * 
	 * @param user
	 * @return
	 */
	public boolean updateUser(User user);

	/**
	 * 更新用户密码
	 * 
	 * @param userId
	 * @param password
	 * @return
	 */
	public boolean updatePassword(long userId, String password);

	/**
	 * 验证用户密码
	 * 
	 * @param userId
	 * @param password
	 * @return
	 */
	public boolean verifyUserPassword(long userId, String password);

	/**
	 * 查询用户
	 * 
	 * @param userId
	 * @return
	 */
	public User queryUser(long userId);

	/**
	 * 查询用户
	 * 
	 * @param username
	 * @return
	 */
	public User queryUser(String username);

	/**
	 * 分页查询用户列表
	 * 
	 * @param query
	 * @return
	 */
	public PageInfo<User> queryUserForPagingList(UserQuery query);

	/**
	 * 查询用户数量
	 * 
	 * @return
	 */
	public int queryUserTotalCount();

}
