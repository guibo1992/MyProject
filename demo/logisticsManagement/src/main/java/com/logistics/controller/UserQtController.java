package com.logistics.controller;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.logistics.entity.UserQt;
import com.logistics.service.UserQtService;

@Controller
@RequestMapping("/")
public class UserQtController {
    @Autowired
    private UserQtService userQtService;

    /*
     * @RequestMapping(value="adduserqt") public String addUserQt(UserQt
     * record)throws Exception{ userQtService.insert(record); return "add1"; }
     */
    @ResponseBody
    @RequestMapping("adduserqt")
    public int adduserqt(UserQt userQt) throws Exception {

        return userQtService.insert(userQt);
    }

    @ResponseBody
    @RequestMapping(value = "loginqt")
    public UserQt Login(UserQt userQt, HttpSession session) throws Exception {
        UserQt userQt1 = userQtService.login(userQt);
        session.setAttribute("userQt1", userQt1);
        session.setMaxInactiveInterval(1000 * 60);
        System.out.println(userQt1);
        return userQt1;

        /*
         * if (userQt!=null&&userQt.getPassword().equals(password)) {
         * System.out.println(22222); return "denglu";
         * 
         * }else { System.out.println(11111); return "index"; }
         */

        /*
         * if(userQt!=null) { model.addAttribute("userQt", userQt); return
         * "index"; }else { return "denglu"; }
         */

    }

}
