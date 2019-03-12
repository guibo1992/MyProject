package com.logistics.controller;

import java.text.SimpleDateFormat;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.propertyeditors.CustomDateEditor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.logistics.entity.Car;
import com.logistics.entity.Invoice;
import com.logistics.service.CarService;
import com.logistics.service.InvoiceService;
import com.logistics.service.UserorderService;

@Controller
@RequestMapping("/")
public class InvoiceController {

    @Autowired
    private InvoiceService invoiceService;

    @Autowired
    private UserorderService userorderService;

    @Autowired
    private CarService carService;

    @InitBinder // 让springmvc支持日期格式转换
    public void init(WebDataBinder wdb) {
        wdb.registerCustomEditor(java.util.Date.class, new CustomDateEditor(new SimpleDateFormat("yyyy-MM-dd"), true));
    }

    @RequestMapping("addinvoice")
    @ResponseBody
    public int addinvoice(Invoice record) throws Exception {
        // Map map = new HashMap();

        // userorderService.deleteByPrimaryKey(record.getId());
        record.setState(3);
        int temp = invoiceService.insertSelective(record);

        return temp;
    }

    @RequestMapping("addnoinvoice")
    @ResponseBody
    public int addnoinvoice(Invoice record) throws Exception {
        // Map map = new HashMap();

        record.setState(2);

        // userorderService.deleteByPrimaryKey(record.getId());

        int temp = invoiceService.insertSelective(record);

        // map.put("temp", temp);
        return temp;
    }

    @RequestMapping("getallinvoice")
    public String getallinvoice(Model model) throws Exception {
        List<Invoice> list = invoiceService.getallinvoice();
        model.addAttribute("list", list);
        List<Car> carn = carService.getallcar();
        model.addAttribute("carn", carn);
        return "所有订单.jsp";

    }

    @RequestMapping("delectinvoice")
    public String delectinvoice(String id) throws Exception {
        int temp = invoiceService.deleteByPrimaryKey(new Integer(id));
        return "getallinvoice";

    }

    @RequestMapping("delectnoinvoice")
    public String delectnoinvoice(String id) throws Exception {
        int temp = invoiceService.deleteByPrimaryKey(new Integer(id));
        return "getnoinvoice";

    }

    @RequestMapping("getnoinvoice")
    public String getnoinvoice(Model model) throws Exception {
        List<Invoice> list = invoiceService.getnoinvoice();
        model.addAttribute("list", list);
        return "未通过审核订单.jsp";

    }

    // -----------------------------

}
