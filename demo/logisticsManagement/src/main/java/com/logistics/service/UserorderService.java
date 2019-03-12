package com.logistics.service;

import java.util.List;

import com.logistics.entity.UserOrder;

public interface UserorderService {
	int insert(UserOrder record);
	
	UserOrder selectByPrimaryKey(Integer id);
	List<UserOrder>  getalluserorder();
    UserOrder getoneuserorder(Integer id);
    int deleteByPrimaryKey(Integer id);
    
}
