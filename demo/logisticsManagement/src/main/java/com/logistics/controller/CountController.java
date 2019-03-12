package com.logistics.controller;

import java.text.SimpleDateFormat;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.propertyeditors.CustomDateEditor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.logistics.entity.StationInvoiceCount;
import com.logistics.service.InvoiceService;

@Controller
@RequestMapping("/")
public class CountController {

    @Autowired
    private InvoiceService invoiceService;

    @InitBinder // 支持日期转换
    public void init(WebDataBinder wdb) {
        wdb.registerCustomEditor(java.util.Date.class, new CustomDateEditor(new SimpleDateFormat("yyyy-MM-dd"), true));
    }

    /**
     * 统计各分理处金额 控制器
     * 
     * @author AK
     * @return stationinvoicecount
     */
    @ResponseBody
    @RequestMapping("easyuigetAllCount")
    public Map<String, Object> easyuigetAllCount(String sendTime, Integer page, Integer rows) throws Exception {
        // 分页
        PageHelper.startPage(page, rows);

        List<StationInvoiceCount> list = invoiceService.getAllCount(sendTime);

        PageInfo<StationInvoiceCount> pageinfo = new PageInfo<>(list);
        System.out.println("当前页码:" + pageinfo.getPageNum());
        System.out.println("页大小:" + pageinfo.getPageSize());
        System.out.println("总页数:" + pageinfo.getPages());
        System.out.println("总记录数:" + pageinfo.getTotal());
        System.out.println("首页:" + pageinfo.getNavigateFirstPage());
        System.out.println("上一页:" + pageinfo.getPrePage());
        System.out.println("下一页:" + pageinfo.getNextPage());
        System.out.println("尾页:" + pageinfo.getNavigateLastPage());
        System.out.println("当前页的记录:" + pageinfo.getList());
        Map<String, Object> data = new HashMap<String, Object>();
        data.put("total", pageinfo.getTotal());
        data.put("rows", pageinfo.getList());
        return data;
        // return "分理处发货排行榜.jsp";
    }

    @RequestMapping("getAllCount")
    public String getAllCount(Model model, String sendTime, Integer number) throws Exception {
        // 分页
        PageHelper.startPage(number == null ? 1 : number, 1);

        List<StationInvoiceCount> list = invoiceService.getAllCount(sendTime);

        PageInfo<StationInvoiceCount> pageinfo = new PageInfo<>(list);

        model.addAttribute("pageinfo", pageinfo);
        return "StationInvoiceCount4.jsp";
        /* return "分理处发货排行榜.jsp"; */
    }

    // 数据统计图
    @ResponseBody
    @RequestMapping("getAllCount2")
    public List<StationInvoiceCount> getAllCount2(String sendTime) throws Exception {
        List<StationInvoiceCount> list = invoiceService.getAllCount(sendTime);

        return list;
        /* return "分理处发货排行榜.jsp"; */
    }

    /*
     * @ResponseBody
     * 
     * @RequestMapping("getSomeCount") public List<StationInvoiceCount>
     * getSomeCount(StationInvoiceCount count) throws Exception{
     * 
     * List<StationInvoiceCount> list=invoiceService.getSomeCount(count); return
     * list; }
     */

    /**
     * 数据导出
     */
    @ResponseBody
    @RequestMapping("layuigetAllCount")
    public Map<String, Object> layuigetAllCount(@RequestParam Map<String, Object> params, String sendTime, Integer page,
            Integer limit) throws Exception {

        // 分页
        PageHelper.startPage(page, limit);

        List<StationInvoiceCount> list = invoiceService.getAllCount(sendTime);

        PageInfo<StationInvoiceCount> pageinfo = new PageInfo<>(list);

        Map<String, Object> map = new HashMap<String, Object>();
        // map.put("code", 0);
        // map.put("msg", "");

        map.put("total", pageinfo.getTotal());
        map.put("rows", pageinfo.getList());
        return map;
        // return "分理处发货排行榜.jsp";
    }

}
