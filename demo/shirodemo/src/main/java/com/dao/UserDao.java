package com.dao;

import java.util.List;
import java.util.Set;

import com.entity.User;

public interface UserDao {
	
	//通过用户名查询用户
	public User getByUserName(String username);
	
	//通过用户名查询角色信息
	public Set<String> getRoles(String username);
	
	//通过用户名查询权限信息
	public Set<String> getPermissions(String username);
	
	
	List<User> getAllUsers();
}
