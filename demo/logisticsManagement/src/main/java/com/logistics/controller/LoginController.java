package com.logistics.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.subject.Subject;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.logistics.entity.WlAdmin;

@Controller
@RequestMapping("/")
public class LoginController {
    @RequestMapping("login")
    public String login() {
        return "login";
    }

    // 登录
    @RequestMapping("loginUser")
    public String loginUser(String wlName, String wlPwd, HttpSession session, HttpServletRequest request) {
        System.out.println("11111111111111111111111111111");
        UsernamePasswordToken token = new UsernamePasswordToken(wlName, wlPwd);
        Subject subject = SecurityUtils.getSubject();
        System.out.println(wlName + wlPwd);
        try {
            token.setRememberMe(false);
            subject.login(token); // 完成登录
            WlAdmin wlAdmin = (WlAdmin) subject.getPrincipal();
            session.setAttribute("AdminInfo", wlAdmin);
            return "htindex.jsp";
        } catch (Exception e) {
            request.setAttribute("info", "登录失败！");
            return "login.jsp";// 返回登录页面
        }
    }

    @RequestMapping("logOut")
    public String logOut(HttpSession session) {
        Subject subject = SecurityUtils.getSubject();
        subject.logout();
        // session.removeAttribute("user");
        return "login.jsp";
    }
}
