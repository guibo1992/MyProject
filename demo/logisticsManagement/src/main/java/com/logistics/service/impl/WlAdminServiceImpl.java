/**
 * Project Name:logisticsManagement
 * File Name:WlAdminServiceImpl.java
 * Package Name:com.logistics.service.impl
 * Date:2018年10月18日下午3:03:42
 * Copyright (c) 2018, bluemobi All Rights Reserved.
 *
*/

package com.logistics.service.impl;

import java.util.List;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.logistics.dao.WlAdminMapper;
import com.logistics.entity.WlAdmin;
import com.logistics.service.WlAdminService;

/**
 * 
 * Description: <br/>
 * date: 2018年10月18日 下午3:04:31 <br/>
 *
 * @author guiB
 * @version
 */

@Service(value = "wlAdminService")
public class WlAdminServiceImpl implements WlAdminService {

    @Autowired
    private WlAdminMapper wlAdminMapper;

    @Override
    public int deleteByPrimaryKey(Integer wlId) {

        // Auto-generated method stub
        return wlAdminMapper.deleteByPrimaryKey(wlId);
    }

    @Override
    public int insert(WlAdmin record) {

        // Auto-generated method stub
        return 0;
    }

    @Override
    public int insertSelective(WlAdmin record) {

        // Auto-generated method stub
        return wlAdminMapper.insertSelective(record);
    }
    /**
     * 查询单条
     */
    @Override
    public WlAdmin selectByPrimaryKey(Integer wlId) {

        // Auto-generated method stub
        return wlAdminMapper.selectByPrimaryKey(wlId);
    }

    /*
     * 修改
     */
    @Override
    public int updateByPrimaryKeySelective(WlAdmin record) {

        // Auto-generated method stub
        return wlAdminMapper.updateByPrimaryKey(record);
    }

    @Override
    public int updateByPrimaryKey(WlAdmin record) {

        // Auto-generated method stub
        return 0;
    }

    /*
     * 登陆
     */
    @Override
    public WlAdmin selectByName(String wlName) {

        // Auto-generated method stub
        return wlAdminMapper.selectByName(wlName);
    }

    @Override
    public Set<String> getRole(String wlName) {

        // Auto-generated method stub
        return wlAdminMapper.getRole(wlName);
    }

    @Override
    public Set<String> getPermission(String wlName) {

        // Auto-generated method stub
        return wlAdminMapper.getPermission(wlName);
    }
   /* public static void main(String[] args) {
    	wlAdminMapper.get
	}*/

	@Override
	public List<WlAdmin> selectAllEmp() {
		// TODO Auto-generated method stub
		return wlAdminMapper.selectAllEmp();
	}

	@Override
	public WlAdmin getWlAdminByPrimaryKey(Integer wlId) {
		// TODO Auto-generated method stub
		return wlAdminMapper.getWlAdminByPrimaryKey(wlId);
	}
}
