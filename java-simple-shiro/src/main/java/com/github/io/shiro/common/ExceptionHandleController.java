package com.github.io.shiro.common;

import org.apache.shiro.authz.AuthorizationException;
import org.apache.shiro.authz.UnauthorizedException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

// TODO 异常统一处理
@ControllerAdvice
public class ExceptionHandleController {
    private static final Logger logger = LoggerFactory.getLogger(ExceptionHandleController.class);

    @ExceptionHandler(UnauthorizedException.class)
    public String handleShiroException(Exception ex) {
        return "no auth error";
    }

    @ExceptionHandler(AuthorizationException.class)
    public String authorizationException(Exception ex) {
        return "no auth error";
    }

}