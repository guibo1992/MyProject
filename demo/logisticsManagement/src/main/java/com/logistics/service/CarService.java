package com.logistics.service;

import java.util.List;

import com.logistics.entity.Car;

public interface CarService {
	List<Car>  getallcar2();
	   List<Car>  getallcar();
	   int updatecarstation(Integer id);
	    /**
	     * This method was generated by MyBatis Generator.
	     * This method corresponds to the database table car
	     *
	     * @mbggenerated Mon Oct 22 08:55:31 CST 2018
	     */
	    int deleteByPrimaryKey(Integer id);

	    /**
	     * This method was generated by MyBatis Generator.
	     * This method corresponds to the database table car
	     *
	     * @mbggenerated Mon Oct 22 08:55:31 CST 2018
	     */
	    int insert(Car record);

	    /**
	     * This method was generated by MyBatis Generator.
	     * This method corresponds to the database table car
	     *
	     * @mbggenerated Mon Oct 22 08:55:31 CST 2018
	     */
	    int insertSelective(Car record);

	    /**
	     * This method was generated by MyBatis Generator.
	     * This method corresponds to the database table car
	     *
	     * @mbggenerated Mon Oct 22 08:55:31 CST 2018
	     */
	    Car selectByPrimaryKey(Integer id);

	    /**
	     * This method was generated by MyBatis Generator.
	     * This method corresponds to the database table car
	     *
	     * @mbggenerated Mon Oct 22 08:55:31 CST 2018
	     */
	    int updateByPrimaryKeySelective(Car record);

	    /**
	     * This method was generated by MyBatis Generator.
	     * This method corresponds to the database table car
	     *
	     * @mbggenerated Mon Oct 22 08:55:31 CST 2018
	     */
	    int updateByPrimaryKey(Car record);
}
