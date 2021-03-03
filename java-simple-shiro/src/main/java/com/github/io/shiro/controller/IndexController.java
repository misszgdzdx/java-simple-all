package com.github.io.shiro.controller;

import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * @description
 */
@Controller
public class IndexController {

    @RequiresPermissions("index")
    @ResponseBody
    @GetMapping(value = "index.json", produces = "application/json;charset=utf-8")
    public Object index() {
        return "success";
    }
}
