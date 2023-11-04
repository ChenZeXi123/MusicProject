package com.czx.bookproject.servletComponent;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;

@WebFilter({"/"})
public class MyFilter implements Filter{

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
        Filter.super.init(filterConfig);
    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        /*HttpServletRequest request=(HttpServletRequest) servletRequest;
        System.out.println("myfilter");
        if (request.getSession().getAttribute("username")==null){
            request.getRequestDispatcher("/register.html").forward(request,servletResponse);
        }else {
            filterChain.doFilter(request,servletResponse);
        }*/
        filterChain.doFilter(servletRequest,servletResponse);
    }

    @Override
    public void destroy() {
        Filter.super.destroy();
    }
}
