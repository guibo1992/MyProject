package com.logistics.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.logistics.dao.CarMapper;
import com.logistics.entity.Car;
import com.logistics.service.CarService;
@Service
public class CarServiceImpl implements CarService {
	@Autowired
	private CarMapper carMapper;
	@Override
	public List<Car> getallcar() {
		// TODO Auto-generated method stub
		return carMapper.getallcar();
	}

	@Override
	public int deleteByPrimaryKey(Integer id) {
		// TODO Auto-generated method stub
		return carMapper.deleteByPrimaryKey(id);
	}

	@Override
	public int insert(Car record) {
		// TODO Auto-generated method stub
		return carMapper.insert(record);
	}

	@Override
	public int insertSelective(Car record) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public Car selectByPrimaryKey(Integer id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public int updateByPrimaryKeySelective(Car record) {
		// TODO Auto-generated method stub
		return carMapper.updateByPrimaryKeySelective(record);
	}

	@Override
	public int updateByPrimaryKey(Car record) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int updatecarstation(Integer id) {
		// TODO Auto-generated method stub
		return carMapper.updatecarstation(id);
	}

	@Override
	public List<Car> getallcar2() {
		// TODO Auto-generated method stub
		return carMapper.getallcar2();
	}

}
