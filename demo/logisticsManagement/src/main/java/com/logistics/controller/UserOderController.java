package com.logistics.controller;

import java.util.List;
import java.util.Random;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.logistics.entity.Car;
import com.logistics.entity.Station;
import com.logistics.entity.UserOrder;
import com.logistics.service.CarService;
import com.logistics.service.InvoiceService;
import com.logistics.service.StationService;
import com.logistics.service.UserorderService;

@Controller
@RequestMapping("/")
public class UserOderController {
    @Autowired
    private CarService carService;

    @Autowired
    private StationService stationService;

    @Autowired
    private InvoiceService invoiceService;

    @Autowired
    private UserorderService userorderService;

    @RequestMapping(value = "adduserorder")
    public String addUserOder(UserOrder record) throws Exception {
        StringBuilder str = new StringBuilder();
        Random random = new Random();
        for (int i = 0; i < 7; i++) {
            str.append(random.nextInt(10));
        }
        int num = (int) Integer.parseInt(str.toString());
        record.setId(num);
        int temp = userorderService.insert(record);
        return "add.jsp";
    }

    @ResponseBody
    @RequestMapping(value = "selectoder")
    public UserOrder selectUserOder(int id, HttpSession session) throws Exception {

        UserOrder userOrder = userorderService.selectByPrimaryKey(id);

        session.setAttribute("userOrder", userOrder);
        session.setMaxInactiveInterval(1000 * 60);
        return userOrder;

    }

    @RequestMapping(value = "zhuxiao")
    public void zhuxiao(HttpServletRequest request, HttpServletResponse response) throws Exception {
        request.getSession().invalidate();

        response.sendRedirect("/logisticsManagement/index.jsp");

    }

    @RequestMapping("getalluserorder")
    public String showtousu(Model model) throws Exception {

        List<UserOrder> list = userorderService.getalluserorder();
        model.addAttribute("list", list);
        return "所有下单.jsp";
    }

    @RequestMapping("getoneuserorder")
    public String getoneuserorder(UserOrder userorder, String id, Model model, HttpSession session) throws Exception {
        userorder = userorderService.selectByPrimaryKey(new Integer(id));
        model.addAttribute("list", userorder);
        List<Car> carname = carService.getallcar();
        model.addAttribute("carname", carname);
        List<Station> sta = stationService.getAllStation();

        session.setAttribute("sta", sta);
        return "下单处理.jsp";
    }

    @ResponseBody
    @RequestMapping("deleUserOrder")
    public int deleUserOrder(Integer id, Integer cid) throws Exception {
        int temp = userorderService.deleteByPrimaryKey(id);
        carService.updatecarstation(cid);
        return temp;
    }
}
