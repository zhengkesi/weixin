package com.weixin.publics.filter;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.util.Map;

/**
 * @Author: xiaodong
 * @Date: 2020/11/3 13:48
 */
public class MyFilter implements Filter {
    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
        System.out.println("过滤器初始化");
    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        HttpServletRequest request = (HttpServletRequest)servletRequest;
        /*Map<String, String[]> parameterMap = request.getParameterMap();
        int size = parameterMap.size();
        System.out.println(size);*/
        String id = request.getParameter("id");
        System.out.println(id);
        System.out.println("执行过滤器");
        filterChain.doFilter(servletRequest,servletResponse);
    }

    @Override
    public void destroy() {
        System.out.println("过滤器销毁");
    }
}
