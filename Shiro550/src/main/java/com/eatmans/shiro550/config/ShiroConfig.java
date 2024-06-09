package com.eatmans.shiro550.config;

import com.eatmans.shiro550.filter.AuthorizationFilter;
import com.eatmans.shiro550.realm.UserRealm;
import org.apache.shiro.spring.web.ShiroFilterFactoryBean;
import org.apache.shiro.web.mgt.CookieRememberMeManager;
import org.apache.shiro.web.mgt.DefaultWebSecurityManager;
import org.apache.shiro.web.servlet.SimpleCookie;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.servlet.Filter;
import java.util.LinkedHashMap;
import java.util.Map;

/**
 * shiro配置类
 */
@Configuration
public class ShiroConfig {

    /**
     * 配置安全管理器
     *
     * @param userRealm UserRealm
     * @return DefaultWebSecurityManager
     */
    @Bean
    public DefaultWebSecurityManager securityManager(UserRealm userRealm) {
        DefaultWebSecurityManager securityManager = new DefaultWebSecurityManager();

        securityManager.setRealm(userRealm);
        // 设置RememberMe管理器
        securityManager.setRememberMeManager(rememberMeManager());
        return securityManager;
    }

    /**
     * 配置Shiro过滤器工厂
     *
     * @param securityManager 安全管理器
     * @return ShiroFilterFactoryBean
     */
    @Bean
    public ShiroFilterFactoryBean shiroFilter(DefaultWebSecurityManager securityManager) {
        ShiroFilterFactoryBean shiroFilterFactoryBean = new ShiroFilterFactoryBean();

        // 注册安全管理器
        shiroFilterFactoryBean.setSecurityManager(securityManager);

        /*
         * 设置登录页面的地址
         * 当用户访问认证资源的时候，如果用户没有登录，那么就会跳转到指定的页面
         */
        shiroFilterFactoryBean.setLoginUrl("/login.html");

        // 定义资源访问规则
        Map<String, String> map = new LinkedHashMap<>();

        /*
         * 过滤器说明
         * anon：不需要认证就可以访问的资源
         * authc：需要登录认证才能访问的资源
         */
        map.put("/admin.html", "authc");
        map.put("/admin", "authc");

        // 不需要认证就能访问
        map.put("/index.html", "anon");
        map.put("/login.html", "anon");
        map.put("/user/login", "anon");

        // 设置自定义过滤器
        map.put("/user/delete", "authorization");
        map.put("/user/update", "authorization");

        shiroFilterFactoryBean.setFilterChainDefinitionMap(map);

        Map<String, Filter> filters = shiroFilterFactoryBean.getFilters();
        filters.put("authorization", new AuthorizationFilter());
        shiroFilterFactoryBean.setFilters(filters);

        shiroFilterFactoryBean.setFilterChainDefinitionMap(map);

        return shiroFilterFactoryBean;
    }

    // 配置RememberMe管理器
    public CookieRememberMeManager rememberMeManager() {
        CookieRememberMeManager rememberMeManager = new CookieRememberMeManager();
        rememberMeManager.setCookie(rememberMeCookie());
        return rememberMeManager;
    }

    // 配置RememberMe Cookie
    public SimpleCookie rememberMeCookie() {
        SimpleCookie simpleCookie = new SimpleCookie("rememberMe");
        simpleCookie.setMaxAge(2592000); // 设置Cookie的有效期为30天
        return simpleCookie;
    }

}