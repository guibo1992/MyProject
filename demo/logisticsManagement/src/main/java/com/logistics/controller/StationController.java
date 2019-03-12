/**
 * Project Name:logisticsManagement
 * File Name:StationController.java
 * Package Name:com.logistics.controller
 * Date:2018年10月13日下午5:20:03
 * Copyright (c) 2018, bluemobi All Rights Reserved.
 *
*/

package com.logistics.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.logistics.entity.Station;
import com.logistics.entity.StationInvoiceCount;
import com.logistics.service.StationService;

/**
 * Description: <br/>
 * Date: 2018年10月13日 下午5:20:03 <br/>
 * 
 * @author guiB
 * @version
 * @see
 */
@Controller
@RequestMapping("/")
public class StationController {

    @Autowired
    private StationService stationService;

    @ResponseBody
    @RequestMapping("showStation")
    public List<Station> showStation() throws Exception {

        return stationService.getAllStation();
    }

    @RequestMapping("showStation2")
    public String showStation2(Model model) throws Exception {

        List<Station> list = stationService.getAllStation();
        System.out.println(list.toString());
        model.addAttribute("list", list);
        return "one";
    }

    @ResponseBody
    @RequestMapping("getSomeCount")
    public List<StationInvoiceCount> getSomeCount(StationInvoiceCount count) throws Exception {
        count.setName("%" + count.getName() + "%");
        List<StationInvoiceCount> list = stationService.getSomeCount(count);

        return list;
    }
}
