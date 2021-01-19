package com.github.io.shiro.controller;

import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class UserController {

    @RequiresPermissions("add.user")
    @ResponseBody
    @RequestMapping("add_user.json")
    public Object addUser() {
        return "success";
    }

}
