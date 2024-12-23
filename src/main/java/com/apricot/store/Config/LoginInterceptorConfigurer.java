package com.apricot.store.Config;

import com.apricot.store.Interceptor.LoginInterceptor;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import java.util.ArrayList;
import java.util.List;


/**
 * 拦截器配置
 *
 */
@Configuration
public class LoginInterceptorConfigurer implements WebMvcConfigurer {

    HandlerInterceptor loginInterceptor = new LoginInterceptor();

    // 注册拦截器
    @Override
    public void addInterceptors(InterceptorRegistry registry) {

        List<String> excludePathPatterns = new ArrayList<>();
        excludePathPatterns.add("/bootstrap3/**");
        excludePathPatterns.add("/js/**");
        excludePathPatterns.add("/css/**");
        excludePathPatterns.add("/images/**");
        excludePathPatterns.add("/web/login.html");
        excludePathPatterns.add("/web/register.html");
        excludePathPatterns.add("/web/logout.html");
        excludePathPatterns.add("/web/index.html");
        excludePathPatterns.add("/web/product.html");
        excludePathPatterns.add("/user/login");
        excludePathPatterns.add("/user/register");
        excludePathPatterns.add("/user/logout");
        excludePathPatterns.add("/address/district/**");
        excludePathPatterns.add("/product/**");

        registry.addInterceptor(loginInterceptor)
                .addPathPatterns("/**") // 拦截所有请求
                .excludePathPatterns(excludePathPatterns); // 排除登录、注册、注销、静态资源
    }

}
