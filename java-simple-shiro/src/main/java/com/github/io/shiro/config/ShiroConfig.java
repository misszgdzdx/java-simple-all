package com.github.io.shiro.config;

import com.github.io.shiro.shirojwt.JwtFilter;
import com.github.io.shiro.shirojwt.JwtRealm;
import org.apache.shiro.mgt.DefaultSessionStorageEvaluator;
import org.apache.shiro.mgt.DefaultSubjectDAO;
import org.apache.shiro.mgt.SecurityManager;
import org.apache.shiro.spring.LifecycleBeanPostProcessor;
import org.apache.shiro.spring.security.interceptor.AuthorizationAttributeSourceAdvisor;
import org.apache.shiro.spring.web.ShiroFilterFactoryBean;
import org.apache.shiro.web.mgt.DefaultWebSecurityManager;
import org.springframework.aop.framework.autoproxy.DefaultAdvisorAutoProxyCreator;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.servlet.Filter;
import java.util.LinkedHashMap;
import java.util.Map;

/**
 * shiro-jwt配置
 */
@Configuration
public class ShiroConfig {

    /**
     * 拦截器，先判断拦截器，再走realm
     */
    @Bean(name = "shiroFilter")
    public ShiroFilterFactoryBean factory(SecurityManager securityManager) {
        ShiroFilterFactoryBean factoryBean = new ShiroFilterFactoryBean();
        Map<String, Filter> filterMap = new LinkedHashMap<>();
        // 设置自定义JWT过滤器，如果需要换名字，就在config中重新注入JwtFilter
        filterMap.put("jwt", new JwtFilter());
        factoryBean.setFilters(filterMap);
        // 设置shiro管理器
        factoryBean.setSecurityManager(securityManager);
        // 设置无权限跳转地址
        factoryBean.setUnauthorizedUrl("/error/401.html");

        Map<String, String> filterChainDefinitionMap = new LinkedHashMap<>();
        // 设置拦截器
        filterChainDefinitionMap.put("/**", "jwt");
        // 不走拦截器
        filterChainDefinitionMap.put("/error/**", "anon");
        factoryBean.setFilterChainDefinitionMap(filterChainDefinitionMap);
        return factoryBean;
    }

    /**
     * 注入securityManager
     */
    @Bean
    public DefaultWebSecurityManager securityManager(JwtRealm jwtRealm) {
        DefaultWebSecurityManager securityManager = new DefaultWebSecurityManager();
        // 设置自定义realm
        securityManager.setRealm(jwtRealm);
        // 关闭shiro自带session
        DefaultSubjectDAO subjectDAO = new DefaultSubjectDAO();
        DefaultSessionStorageEvaluator defaultSessionStorageEvaluator = new DefaultSessionStorageEvaluator();
        defaultSessionStorageEvaluator.setSessionStorageEnabled(false);
        subjectDAO.setSessionStorageEvaluator(defaultSessionStorageEvaluator);
        securityManager.setSubjectDAO(subjectDAO);
        return securityManager;
    }

    /**
     * 添加注解支持，可以在controller层使用注解
     */
    @Bean
    public DefaultAdvisorAutoProxyCreator defaultAdvisorAutoProxyCreator() {
        DefaultAdvisorAutoProxyCreator defaultAdvisorAutoProxyCreator = new DefaultAdvisorAutoProxyCreator();
        defaultAdvisorAutoProxyCreator.setProxyTargetClass(true);
        return defaultAdvisorAutoProxyCreator;
    }

    @Bean
    public AuthorizationAttributeSourceAdvisor authorizationAttributeSourceAdvisor(SecurityManager securityManager) {
        AuthorizationAttributeSourceAdvisor advisor = new AuthorizationAttributeSourceAdvisor();
        advisor.setSecurityManager(securityManager);
        return advisor;
    }

    @Bean
    public LifecycleBeanPostProcessor lifecycleBeanPostProcessor() {
        return new LifecycleBeanPostProcessor();
    }
}