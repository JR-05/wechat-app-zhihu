package com.jr.zhihu.servlet;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/*
    authod:JR
    1. 测试Web组件的自动装配
    2. 证明servlet每次处理请求的整个过程都是在通过一个子线程下进行的,包括从过滤器，Servlet；拦截器，处理器
 */
@WebServlet(urlPatterns = "/myservlet")
public class MyServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        resp.setContentType("text/html;charset=utf-8");
        resp.getWriter().print("对象地址："+this.hashCode()+"线程ID："+Thread.currentThread().getId());
    }
}
