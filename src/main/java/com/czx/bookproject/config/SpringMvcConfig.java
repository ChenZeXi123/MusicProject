package com.czx.bookproject.config;

import com.czx.bookproject.interceptors.FirstInterceptors;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.*;

@Configuration

public class SpringMvcConfig implements WebMvcConfigurer {
    @Override
    public void addViewControllers(ViewControllerRegistry registry) {
        //直接访问首页
        registry.addViewController("/").setViewName("index");
        //点击首页的登录后,访问登录页
        registry.addViewController("/login").setViewName("login");
        //点击首页的注册后,访问登录页
        registry.addViewController("/register").setViewName("register");
    }

    //添加拦截器
    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        /*
        拦截器拦截：
        1.用户点击个人音乐按键,用于判断是否已经为登录状态。
        2.添加至我的音乐时,要判断是否有登录。

        注意：如果添加的拦截路径为"/**",那么并且要排除拦截器preHandle方法中进行的页面转发的请求路径
        */
        registry.addInterceptor(new FirstInterceptors()).addPathPatterns("/page/personalMusic.html");
    }
}
