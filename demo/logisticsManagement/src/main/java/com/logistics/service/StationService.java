/**
 * Project Name:logisticsManagement
 * File Name:StationService.java
 * Package Name:com.logistics.service
 * Date:2018年10月13日下午5:15:09
 * Copyright (c) 2018, bluemobi All Rights Reserved.
 *
*/

package com.logistics.service;

import java.util.List;

import com.logistics.entity.Station;
import com.logistics.entity.StationInvoiceCount;

/**
 * Description: <br/>
 * Date: 2018年10月13日 下午5:15:09 <br/>
 * 
 * @author guiB
 * @version
 * @see
 */
public interface StationService {
    List<Station> getAllStation();

    /**
     * 动态条件查询所有
     */
    public List<StationInvoiceCount> getSomeCount(StationInvoiceCount count);
}
