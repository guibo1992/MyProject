package com.dao;

import java.util.List;
import java.util.Set;

import com.entity.User;

public interface UserDao {
	
	//ͨ���û�����ѯ�û�
	public User getByUserName(String username);
	
	//ͨ���û�����ѯ��ɫ��Ϣ
	public Set<String> getRoles(String username);
	
	//ͨ���û�����ѯȨ����Ϣ
	public Set<String> getPermissions(String username);
	
	
	List<User> getAllUsers();
}
