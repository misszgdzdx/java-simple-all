package com.github.io.shiro.controller;

import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class UserController {

    @RequiresPermissions("add.user")
    @ResponseBody
    @PostMapping(value = "add_user.json", produces = "application/json;charset=utf-8")
    public Object addUser() {
        return "success";
    }

}
