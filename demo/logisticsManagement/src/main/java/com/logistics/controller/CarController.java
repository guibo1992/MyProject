package com.logistics.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.logistics.entity.Car;
import com.logistics.service.CarService;

@Controller
@RequestMapping("/")
public class CarController {
    @Autowired
    private CarService carService;

    @RequestMapping("addcar")
    @ResponseBody
    public int addcaraddcar(Car car) throws Exception {
        int temp = carService.insert(car);

        return temp;
    }

    @RequestMapping("showcar")
    public String showcar(Model model) throws Exception {
        List<Car> list = carService.getallcar2();
        model.addAttribute("list", list);
        return "所有车辆.jsp";
    }

    @RequestMapping("deletecar")
    public String deletecar(Integer id) throws Exception {
        int temp = carService.deleteByPrimaryKey(id);

        return "showcar";
    }
}
