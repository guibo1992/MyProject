package com.service.impl;

import java.util.List;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.dao.UserDao;
import com.entity.User;
import com.service.UserService;

@Service("userService")
public class UserServiceImpl implements UserService {
	
	@Autowired
	private UserDao userDao;

	@Override
	public User getByUserName(String username) {
		// TODO Auto-generated method stub
		return userDao.getByUserName(username);
	}

	@Override
	public Set<String> getRoles(String username) {
		// TODO Auto-generated method stub
		return userDao.getRoles(username);
	}

	@Override
	public Set<String> getPermissions(String username) {
		// TODO Auto-generated method stub
		return userDao.getPermissions(username);
	}

	@Override
	public List<User> getAllUsers() {
		// TODO Auto-generated method stub
		return userDao.getAllUsers();
	}

}
