package com.github.io.shiro.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * @description
 */
@Controller
public class LoginController {

    /**
     * 跳转登录页
     */
    @RequestMapping("/")
    public String goLogin() {
        return "";
    }

    /**
     * 登录
     */
    @ResponseBody
    @RequestMapping("login_in.html")
    public Object loginIn() {
        return "";
    }
}
