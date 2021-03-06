package com.logistics.dao;

import java.util.List;

import com.logistics.entity.Invoice;
import com.logistics.entity.StationInvoiceCount;

public interface InvoiceMapper {
    List<Invoice> getallinvoice();

    List<Invoice> getnoinvoice();

    /**
     * This method was generated by MyBatis Generator. This method corresponds
     * to the database table invoice
     *
     * @mbggenerated Sat Oct 13 16:51:59 CST 2018
     */
    int deleteByPrimaryKey(Integer id);

    /**
     * This method was generated by MyBatis Generator. This method corresponds
     * to the database table invoice
     *
     * @mbggenerated Sat Oct 13 16:51:59 CST 2018
     */
    int insert(Invoice record);

    /**
     * This method was generated by MyBatis Generator. This method corresponds
     * to the database table invoice
     *
     * @mbggenerated Sat Oct 13 16:51:59 CST 2018
     */
    int insertSelective(Invoice record);

    /**
     * This method was generated by MyBatis Generator. This method corresponds
     * to the database table invoice
     *
     * @mbggenerated Sat Oct 13 16:51:59 CST 2018
     */
    Invoice selectByPrimaryKey(Integer id);

    /**
     * This method was generated by MyBatis Generator. This method corresponds
     * to the database table invoice
     *
     * @mbggenerated Sat Oct 13 16:51:59 CST 2018
     */
    int updateByPrimaryKeySelective(Invoice record);

    /**
     * This method was generated by MyBatis Generator. This method corresponds
     * to the database table invoice
     *
     * @mbggenerated Sat Oct 13 16:51:59 CST 2018
     */
    int updateByPrimaryKey(Invoice record);

    /**
     * 统计所有分理处金额
     */
    public List<StationInvoiceCount> getAllCount(String sendTime);

    /**
     * 动态条件查询所有
     */
    public List<StationInvoiceCount> getSomeCount(StationInvoiceCount count);

    /**
     * 数据表格导出
     */
    public List<StationInvoiceCount> teacherinfor(String sendTime);
}