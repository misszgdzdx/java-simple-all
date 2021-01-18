package com.github.io.shiro.shirojwt;

import com.github.io.shiro.common.Constants;
import com.github.io.shiro.entity.User;
import com.github.io.shiro.service.UserRoleService;
import com.github.io.shiro.service.UserService;
import org.apache.commons.lang3.StringUtils;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.AuthenticationInfo;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authc.SimpleAuthenticationInfo;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.apache.shiro.util.ByteSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.util.CollectionUtils;
import org.springframework.util.ObjectUtils;

import java.util.List;

/**
 * 自定义realm
 */
@Component
public class JwtRealm extends AuthorizingRealm {

    @Autowired
    private UserService userService;

    @Autowired
    private UserRoleService userRoleService;

    /**
     * 必须重写此方法，不然shiro会报错
     */
    @Override
    public boolean supports(AuthenticationToken token) {
        return token instanceof JwtToken;
    }

    /**
     * 授权
     */
    @Override
    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principals) {
        SimpleAuthorizationInfo simpleAuthorizationInfo = new SimpleAuthorizationInfo();
        // 获取认证后的用户信息
        User user = (User) principals.getPrimaryPrincipal();
        if (ObjectUtils.isEmpty(user)) {
            // TODO 日志打印
            return null;
        }
        List<String> authList = userRoleService.getRoleByRoleId(user.getRoleId());
        if (CollectionUtils.isEmpty(authList)) {
            return null;
        }
        // 添加权限集
        simpleAuthorizationInfo.addRoles(authList);
        return simpleAuthorizationInfo;
    }

    /**
     * 认证
     */
    @Override
    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken authenticationToken) throws AuthenticationException {
        String token = (String) authenticationToken.getCredentials();
        String username = JwtUtil.getUsername(token);
        if (StringUtils.isBlank(username) || !JwtUtil.verify(token, username, Constants.JWT_SECRET)) {
            throw new AuthenticationException("用户token信息无效");
        }
        User user = userService.getUserByName(username);
        if (ObjectUtils.isEmpty(user)) {
            throw new AuthenticationException("用户名或密码错误");
        }
        return new SimpleAuthenticationInfo(user, token, ByteSource.Util.bytes(Constants.REALM_SALT), getName());
    }
}
