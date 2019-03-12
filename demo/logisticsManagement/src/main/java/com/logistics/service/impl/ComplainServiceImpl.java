package com.logistics.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.logistics.dao.ComplainMapper;
import com.logistics.entity.Complain;
import com.logistics.service.ComplainService;
@Service
public class ComplainServiceImpl implements ComplainService {
@Autowired
private ComplainMapper complainMapper;
	@Override
	public int deleteByPrimaryKey(Integer id) {
		// TODO Auto-generated method stub
		return complainMapper.deleteByPrimaryKey(id);
	}

	@Override
	public int insert(Complain record) {
		
		return complainMapper.insert(record);
	}

	@Override
	public int insertSelective(Complain record) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public Complain selectByPrimaryKey(Integer id) {
		// TODO Auto-generated method stub
		return complainMapper.selectByPrimaryKey(id);
	}

	@Override
	public int updateByPrimaryKeySelective(Complain record) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int updateByPrimaryKey(Complain record) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public List<Complain> getallcomplain() {
		
		return complainMapper.getallcomplain();
	}

}
