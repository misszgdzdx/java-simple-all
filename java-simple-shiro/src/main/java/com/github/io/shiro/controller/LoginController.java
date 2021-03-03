package com.github.io.shiro.controller;

import com.github.io.shiro.common.Constants;
import com.github.io.shiro.entity.User;
import com.github.io.shiro.service.UserService;
import com.github.io.shiro.shirojwt.JwtUtil;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Controller;
import org.springframework.util.ObjectUtils;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.TimeUnit;

/**
 * @description
 */
@Controller
public class LoginController {

    @Autowired
    UserService userService;

    @Autowired
    RedisTemplate<String, String> redisTemplate;

    private Long redisRefreshTime;

    @Value("${jwt.redis.refresh.time}")
    private void setRefreshTime(String time) {
        if (StringUtils.isBlank(time)) {
            time = Constants.JWT_EXPIRE_TIME;
        }
        redisRefreshTime = Long.valueOf(time);
    }

    /**
     * 跳转登录页
     */
    @GetMapping("/")
    public String goLogin() {
        return "welcome";
    }

    /**
     * 登录
     */
    @ResponseBody
    @PostMapping(value = "login_in.json", produces = "application/json;charset=utf-8")
    public Object loginIn(@RequestBody User user) {
        User adminUser = userService.getUserByName(user.getUsername());
        if (ObjectUtils.isEmpty(adminUser)) {
            return "用户名或密码错误";
        }

        String token = JwtUtil.sign(user.getUsername(), Constants.JWT_SECRET);
        String redisKey = Constants.USER_TOKEN_REFRESH_REDIS_KEY + adminUser.getUsername();
        redisTemplate.opsForValue().set(redisKey, token, redisRefreshTime, TimeUnit.MINUTES);

        Map<String, Object> retMap = new HashMap<>();
        retMap.put("authToken", token);
        return retMap;
    }
}
