package com.github.io.shiro.service.impl;

import com.github.io.shiro.entity.User;
import com.github.io.shiro.service.UserService;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl implements UserService {

    @Override
    public User getUserByName(String username) {
        User user = null;
        if ("tom".equals(username)) {
            user = new User();
            user.setUsername("tom");
            user.setPassword("123456");
            user.setRoleId(1); // 1超管
        } else if ("bob".equals(username)) {
            user = new User();
            user.setUsername("bob");
            user.setPassword("654321");
            user.setRoleId(2); // 2普通
        }
        return user;
    }
}
