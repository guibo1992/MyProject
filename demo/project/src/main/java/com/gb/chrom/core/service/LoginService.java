package com.gb.chrom.core.service;

import org.apache.shiro.authc.AuthenticationException;

/**
 * 登录服务
 * 
 * @author Summer
 *
 *         2017年10月15日
 */
public interface LoginService<T> {

	/**
	 * 登录
	 * 
	 * @param username
	 * @return
	 * @throws AuthenticationException
	 */
	public T login(String username) throws AuthenticationException;

	/**
	 * 登录后
	 * 
	 * @param userId
	 */
	public void afterLogin(long userId);

}
