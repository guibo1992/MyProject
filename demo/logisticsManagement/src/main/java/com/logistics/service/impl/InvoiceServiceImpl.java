package com.logistics.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.logistics.dao.InvoiceMapper;
import com.logistics.entity.Invoice;
import com.logistics.entity.StationInvoiceCount;
import com.logistics.service.InvoiceService;

@Service(value = "invoiceService")
public class InvoiceServiceImpl implements InvoiceService {

    @Autowired
    private InvoiceMapper invoiceMapper;

    @Override
    /**
     * 统计所有分理处金额
     */
    public List<StationInvoiceCount> getAllCount(String sendTime) {
        // TODO Auto-generated method stub
        return invoiceMapper.getAllCount(sendTime);
    }

    @Override
    /**
     * 动态查询所有
     */
    public List<StationInvoiceCount> getSomeCount(StationInvoiceCount count) {
        // TODO Auto-generated method stub
        return invoiceMapper.getSomeCount(count);
    }
    // --------------------------------------------

    @Override
    public List<Invoice> getallinvoice() {

        // Auto-generated method stub
        return invoiceMapper.getallinvoice();
    }

    @Override
    public List<Invoice> getnoinvoice() {

        // Auto-generated method stub
        return invoiceMapper.getnoinvoice();
    }

    @Override
    public int deleteByPrimaryKey(Integer id) {

        // Auto-generated method stub
        return invoiceMapper.deleteByPrimaryKey(id);
    }

    @Override
    public int insert(Invoice record) {

        // Auto-generated method stub
        return invoiceMapper.insert(record);
    }

    @Override
    public int insertSelective(Invoice record) {

        // Auto-generated method stub
        return invoiceMapper.insert(record);
    }

    @Override
    public Invoice selectByPrimaryKey(Integer id) {

        // Auto-generated method stub
        return null;
    }

    @Override
    public int updateByPrimaryKeySelective(Invoice record) {

        // Auto-generated method stub
        return 0;
    }

    @Override
    public int updateByPrimaryKey(Invoice record) {

        // Auto-generated method stub
        return 0;
    }

}
