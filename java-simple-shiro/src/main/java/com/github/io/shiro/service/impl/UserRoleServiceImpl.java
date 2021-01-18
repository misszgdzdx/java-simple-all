package com.github.io.shiro.service.impl;

import com.github.io.shiro.service.UserRoleService;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class UserRoleServiceImpl implements UserRoleService {

    @Override
    public List<String> getRoleByRoleId(Integer roleId) {
        List<String> list = new ArrayList<>();
        if (roleId == 1) {
            list.add("index");
            list.add("add.user");
        } else if (roleId == 2) {
            list.add("index");
        }
        return list;
    }
}
