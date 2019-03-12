/**
 * Project Name:logisticsManagement
 * File Name:StationServiceImpl.java
 * Package Name:com.logistics.service.impl
 * Date:2018年10月13日下午5:16:02
 * Copyright (c) 2018, bluemobi All Rights Reserved.
 *
*/

package com.logistics.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.logistics.dao.StationMapper;
import com.logistics.entity.Station;
import com.logistics.entity.StationInvoiceCount;
import com.logistics.service.StationService;

/**
 * Description: <br/>
 * Date: 2018年10月13日 下午5:16:02 <br/>
 * 
 * @author guiB
 * @version
 * @see
 */
@Service(value = "stationService")
public class StationServiceImpl implements StationService {
    @Autowired
    private StationMapper stationMapper;

    @Override
    public List<Station> getAllStation() {

        // Auto-generated method stub
        return stationMapper.getAllStation();
    }

    /**
     * 动态查询
     */
    @Override
    public List<StationInvoiceCount> getSomeCount(StationInvoiceCount count) {
        // TODO Auto-generated method stub
        return stationMapper.getSomeCount(count);
    }

}
