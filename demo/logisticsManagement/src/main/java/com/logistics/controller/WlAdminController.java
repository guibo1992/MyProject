/**
 * Project Name:logisticsManagement

 * File Name:WlAdminController.java
 * Package Name:com.logistics.controller
 * Date:2018年10月16日下午5:24:04
 * Copyright (c) 2018, bluemobi All Rights Reserved.
 *
*/

package com.logistics.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.logistics.entity.WlAdmin;
import com.logistics.service.RodeService;
import com.logistics.service.WlAdminService;

/**
 * Description: <br/>
 * Date: 2018年10月16日 下午5:24:04 <br/>
 * 
 * @author guiB
 * @version
 * @see
 */
@Controller
@RequestMapping("/")
public class WlAdminController {

    @Autowired
    private WlAdminService wlAdminService;

    @Autowired
    private RodeService rodeService;

    // 查询单个用户信息
    @RequestMapping("selectUser")
    public String changeRole(Integer wlId, Model model) throws Exception {
        model.addAttribute("wlAdmin", wlAdminService.getWlAdminByPrimaryKey((Integer) wlId));
        model.addAttribute("roles", rodeService.getAllRole());
        return "changePermission.jsp";
    }

    // 查询所有用户信息
    @ResponseBody
    @RequestMapping("selectAllEmp")
    public Map<String, Object> selectAllEmp(@RequestParam Map<String, Object> params, Integer page, Integer limit)
            throws Exception {
        PageHelper.startPage(page, limit);
        List<WlAdmin> list = wlAdminService.selectAllEmp();
        PageInfo<WlAdmin> pageInfo = new PageInfo<>(list);
        Map<String, Object> data = new HashMap<String, Object>();
        data.put("total", pageInfo.getTotal());
        data.put("rows", pageInfo.getList());
        return data;

    }

    // 改变用户角色
    @ResponseBody
    @RequestMapping("changeAdminRole")
    public int changeAdminRole(WlAdmin wlAdmin) throws Exception {
        System.out.println(wlAdmin.getWlPwd());
        System.out.println(wlAdmin.getTruename());
        System.out.println(wlAdmin.getWlId());
        System.out.println(wlAdmin.getRoleid());
        return wlAdminService.updateByPrimaryKeySelective(wlAdmin);
    }

    /*
     * 注册用户
     */
    @ResponseBody
    @RequestMapping(value = "addSucceed")
    public int addEmpInfo(String wlName, String truename, String wlPwd) throws Exception {
        WlAdmin wlAdmin = new WlAdmin();
        Integer wlId = (int) ((Math.random() * 9 + 1) * 1000000);
        wlAdmin.setWlId(wlId);
        wlAdmin.setWlName(wlName);
        wlAdmin.setTruename(truename);
        wlAdmin.setWlPwd(wlPwd);
        System.out.println(wlAdminService.insertSelective(wlAdmin));
        return (int) wlAdminService.insertSelective(wlAdmin);

    }

    /*
     * 删除
     */
    @ResponseBody
    @RequestMapping(value = "delAdmin")
    public int delAdmin(Integer wlId) throws Exception {
        int temp = wlAdminService.deleteByPrimaryKey((int) wlId);
        if (temp > 0) {
            return 1;
        } else {
            return 0;
        }
    }

}
