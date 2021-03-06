package com.logistics.dao;

import java.util.List;

import com.logistics.entity.Station;
import com.logistics.entity.StationInvoiceCount;

public interface StationMapper {

    /**
     * 动态条件查询所有
     */
    public List<StationInvoiceCount> getSomeCount(StationInvoiceCount count);

    List<Station> getAllStationCount(String starttime, String endtime, String name);

    List<Station> getAllStation();

    /**
     * This method was generated by MyBatis Generator. This method corresponds
     * to the database table station
     *
     * @mbggenerated Sat Oct 13 16:51:59 CST 2018
     */
    int deleteByPrimaryKey(Integer id);

    /**
     * This method was generated by MyBatis Generator. This method corresponds
     * to the database table station
     *
     * @mbggenerated Sat Oct 13 16:51:59 CST 2018
     */
    int insert(Station record);

    /**
     * This method was generated by MyBatis Generator. This method corresponds
     * to the database table station
     *
     * @mbggenerated Sat Oct 13 16:51:59 CST 2018
     */
    int insertSelective(Station record);

    /**
     * This method was generated by MyBatis Generator. This method corresponds
     * to the database table station
     *
     * @mbggenerated Sat Oct 13 16:51:59 CST 2018
     */
    Station selectByPrimaryKey(Integer id);

    /**
     * This method was generated by MyBatis Generator. This method corresponds
     * to the database table station
     *
     * @mbggenerated Sat Oct 13 16:51:59 CST 2018
     */
    int updateByPrimaryKeySelective(Station record);

    /**
     * This method was generated by MyBatis Generator. This method corresponds
     * to the database table station
     *
     * @mbggenerated Sat Oct 13 16:51:59 CST 2018
     */
    int updateByPrimaryKey(Station record);
}