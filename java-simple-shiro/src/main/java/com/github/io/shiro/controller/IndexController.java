package com.github.io.shiro.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * @description
 */
@Controller
public class IndexController {

    @ResponseBody
    @RequestMapping("index.json")
    public Object index() {
        return "success";
    }
}
