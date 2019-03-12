package com.logistics.shiro;

import java.util.LinkedHashMap;

import org.apache.shiro.mgt.SecurityManager;
import org.apache.shiro.spring.LifecycleBeanPostProcessor;
import org.apache.shiro.spring.security.interceptor.AuthorizationAttributeSourceAdvisor;
import org.apache.shiro.spring.web.ShiroFilterFactoryBean;
import org.apache.shiro.web.mgt.DefaultWebSecurityManager;
import org.springframework.aop.framework.autoproxy.DefaultAdvisorAutoProxyCreator;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.filter.DelegatingFilterProxy;

/**
 * shiro的配置类
 * 
 * @author Administrator
 *
 */

@Configuration
public class ShiroConfiguration {
    @Bean
    public FilterRegistrationBean delegatingFilterProxy() {
        FilterRegistrationBean FilterRegistrationBean = new FilterRegistrationBean();
        DelegatingFilterProxy proxy = new DelegatingFilterProxy();
        proxy.setTargetFilterLifecycle(true);
        proxy.setTargetBeanName("shiroFilter");
        FilterRegistrationBean.setFilter(proxy);
        return FilterRegistrationBean;
    }

    @Bean(name = "shiroFilter")
    public ShiroFilterFactoryBean shiroFilter(@Qualifier("securityManager") SecurityManager manager) {
        ShiroFilterFactoryBean bean = new ShiroFilterFactoryBean();
        bean.setSecurityManager(manager);
        // 配置登录的url
        bean.setLoginUrl("/login.jsp");
        // 登录成功的url
        bean.setSuccessUrl("/htindex.jsp");
        // 配置访问权限
        LinkedHashMap<String, String> filterChainDefinitionMap = new LinkedHashMap<>();
        /*
         * filterChainDefinitionMap.put("/jsp/login.jsp*", "anon"); //表示可以匿名访问
         */

        filterChainDefinitionMap.put("/loginUser", "anon");
        filterChainDefinitionMap.put("/addEmpInfo.jsp", "anon");
        filterChainDefinitionMap.put("/index.jsp*", "anon"); // 表示可以匿名访问
        /* filterChainDefinitionMap.put("/htindex.jsp", "anon"); */
        filterChainDefinitionMap.put("/所有车辆.jsp", "anon");
        filterChainDefinitionMap.put("/showcar.jsp", "anon");

        filterChainDefinitionMap.put("/denglu.jsp", "anon");
        // 静态资源放行
        filterChainDefinitionMap.put("/css", "anon");
        filterChainDefinitionMap.put("/img", "anon");
        filterChainDefinitionMap.put("/js", "anon");
        filterChainDefinitionMap.put("/layui", "anon");
        // filterChainDefinitionMap.put("/css", "anon");
        filterChainDefinitionMap.put("/static", "anon");

        filterChainDefinitionMap.put("/logout*", "anon");
        filterChainDefinitionMap.put("/jsp/error.jsp*", "anon");
        filterChainDefinitionMap.put("/jsp/htindex.jsp*", "authc");
        filterChainDefinitionMap.put("/*", "authc");// 表示需要认证才可以访问
        filterChainDefinitionMap.put("/**", "authc");// 表示需要认证才可以访问
        filterChainDefinitionMap.put("/*.*", "authc");
        bean.setFilterChainDefinitionMap(filterChainDefinitionMap);
        return bean;
    }

    // shiro安全管理器
    @Bean(name = "securityManager")
    public SecurityManager securityManager(@Qualifier("authRealm") AuthRealm authRealm) {
        System.out.println("--------------shiro已经加载----------------");
        DefaultWebSecurityManager manager = new DefaultWebSecurityManager();
        // 设置realm
        manager.setRealm(authRealm);
        return manager;
    }

    // 配置自定义的权限登录器
    @Bean(name = "authRealm")
    public AuthRealm authRealm(@Qualifier("credentialsMatcher") CredentialsMatcher matcher) {
        AuthRealm authRealm = new AuthRealm();
        authRealm.setCredentialsMatcher(matcher);
        return authRealm;
    }

    // 配置自定义的密码比较器
    @Bean(name = "credentialsMatcher")
    public CredentialsMatcher credentialsMatcher() {
        return new CredentialsMatcher();
    }

    @Bean
    public LifecycleBeanPostProcessor lifecycleBeanPostProcessor() {
        return new LifecycleBeanPostProcessor();
    }

    /**
     * 自动代理所有的advisor: 由Advisor决定对哪些类的方法进行AOP代理。
     */
    @Bean
    public DefaultAdvisorAutoProxyCreator defaultAdvisorAutoProxyCreator() {
        DefaultAdvisorAutoProxyCreator creator = new DefaultAdvisorAutoProxyCreator();
        creator.setProxyTargetClass(true);
        return creator;
    }

    @Bean
    public AuthorizationAttributeSourceAdvisor authorizationAttributeSourceAdvisor(
            @Qualifier("securityManager") SecurityManager manager) {
        AuthorizationAttributeSourceAdvisor advisor = new AuthorizationAttributeSourceAdvisor();
        advisor.setSecurityManager(manager);
        return advisor;
    }

}
