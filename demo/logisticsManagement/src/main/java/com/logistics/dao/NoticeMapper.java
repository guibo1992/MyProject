package com.logistics.dao;

import com.logistics.entity.Notice;

public interface NoticeMapper {
    /**
     * This method was generated by MyBatis Generator. This method corresponds
     * to the database table notice
     *
     * @mbggenerated Fri Oct 26 08:35:03 CST 2018
     */
    int deleteByPrimaryKey(Integer id);

    /**
     * This method was generated by MyBatis Generator. This method corresponds
     * to the database table notice
     *
     * @mbggenerated Fri Oct 26 08:35:03 CST 2018
     */
    int insert(Notice record);

    /**
     * This method was generated by MyBatis Generator. This method corresponds
     * to the database table notice
     *
     * @mbggenerated Fri Oct 26 08:35:03 CST 2018
     */
    int insertSelective(Notice record);

    /**
     * This method was generated by MyBatis Generator. This method corresponds
     * to the database table notice
     *
     * @mbggenerated Fri Oct 26 08:35:03 CST 2018
     */
    Notice selectByPrimaryKey(Integer id);

    /**
     * This method was generated by MyBatis Generator. This method corresponds
     * to the database table notice
     *
     * @mbggenerated Fri Oct 26 08:35:03 CST 2018
     */
    int updateByPrimaryKeySelective(Notice record);

    /**
     * This method was generated by MyBatis Generator. This method corresponds
     * to the database table notice
     *
     * @mbggenerated Fri Oct 26 08:35:03 CST 2018
     */
    int updateByPrimaryKey(Notice record);
}