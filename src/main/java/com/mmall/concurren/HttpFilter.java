package com.mmall.concurren;

import com.mmall.concurren.example.ThreadLocal.RequestHolder;
import lombok.extern.slf4j.Slf4j;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;

@Slf4j
public class HttpFilter implements Filter{
    @Override
    public void init(FilterConfig filterConfig){}

    @Override
    public void  doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws ServletException,IOException{
        HttpServletRequest request = (HttpServletRequest) servletRequest;
        log.info("do Filter, {}, {} ",Thread.currentThread().getId(),request.getServletPath());
        RequestHolder.add(Thread.currentThread().getId());
        filterChain.doFilter(servletRequest, servletResponse);
    }

    @Override
    public void destroy(){}
}
