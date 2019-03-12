package com.logistics.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.logistics.dao.UserQtMapper;
import com.logistics.entity.UserQt;
import com.logistics.service.UserQtService;


@Service
public class UserQtServiceImpl implements UserQtService {
	
	@Autowired
	private UserQtMapper userQtMapper;

	@Override
	@Transactional
	public int insert(UserQt userQt) {
		// TODO Auto-generated method stub
		return userQtMapper.insert(userQt);
	}

	@Override
	public UserQt login( UserQt userQt) {
		// TODO Auto-generated method stub
		return userQtMapper.login(userQt);
	}

	

	

}
