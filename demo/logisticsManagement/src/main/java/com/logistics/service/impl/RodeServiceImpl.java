package com.logistics.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.logistics.dao.RodeMapper;
import com.logistics.entity.Rode;
import com.logistics.service.RodeService;


@Service(value="rodeService")
public class RodeServiceImpl implements RodeService {

	@Autowired RodeMapper rodeMapper;
	
	@Override
	public List<Rode> getAllRole() {
		// TODO Auto-generated method stub
		return rodeMapper.getAllRole();
	}

}
