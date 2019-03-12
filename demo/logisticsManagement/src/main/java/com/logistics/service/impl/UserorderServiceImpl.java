package com.logistics.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.logistics.dao.UserOrderMapper;
import com.logistics.entity.UserOrder;
import com.logistics.service.UserorderService;

@Service
public class UserorderServiceImpl implements UserorderService {
	@Autowired
	private UserOrderMapper userOrderMapper;

	@Override
	@Transactional
	public int insert(UserOrder record) {
		// TODO Auto-generated method stub
		return userOrderMapper.insert(record);
	}

	@Override
	public UserOrder selectByPrimaryKey(Integer id) {
		// TODO Auto-generated method stub
		return userOrderMapper.selectByPrimaryKey(id);
	}

	@Override
	public List<UserOrder> getalluserorder() {
		// TODO Auto-generated method stub
		return userOrderMapper.getalluserorder();
	}

	@Override
	public UserOrder getoneuserorder(Integer id) {
		// TODO Auto-generated method stub
		return userOrderMapper.getoneuserorder(id);
	}

	@Override
	public int deleteByPrimaryKey(Integer id) {
		// TODO Auto-generated method stub
		return userOrderMapper.deleteByPrimaryKey(id);
	}

}
