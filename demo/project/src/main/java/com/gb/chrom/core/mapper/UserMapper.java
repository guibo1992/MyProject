package com.gb.chrom.core.mapper;

import org.apache.ibatis.annotations.Mapper;

import com.gb.chrom.core.MapperException;
import com.gb.chrom.model.User;
import com.gb.chrom.model.query.UserQuery;
import com.github.pagehelper.Page;

/**
 * 用户 Mapper
 * 
 * @author Summer
 */
@Mapper
public interface UserMapper {

	/**
	 * 保存用户
	 * 
	 * @param user
	 * @return
	 * @throws MapperException
	 */
	Integer saveUser(User user) throws MapperException;

	/**
	 * 更新用户
	 * 
	 * @param user
	 * @return
	 * @throws MapperException
	 */
	Integer updateUser(User user) throws MapperException;

	/**
	 * 根据 ID 查询用户
	 * 
	 * @param id
	 * @return
	 * @throws MapperException
	 */
	User findUserById(Long id) throws MapperException;

	/**
	 * 根据 username 查询用户
	 * 
	 * @param username
	 * @return
	 * @throws MapperException
	 */
	User findUserByUsername(String username) throws MapperException;

	/**
	 * 查询用户列表
	 * 
	 * @param query
	 * @return
	 * @throws MapperException
	 */
	Page<User> findUserForList(UserQuery query) throws MapperException;

	/**
	 * 查询用户数量
	 * 
	 * @return
	 * @throws MapperException
	 */
	Integer findUserTotalCount() throws MapperException;

}
