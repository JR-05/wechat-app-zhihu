package com.jr.zhihu.interceptor;

import com.alibaba.fastjson.JSON;
import com.jr.zhihu.bean.ResultBean;
import com.jr.zhihu.service.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.servlet.HandlerInterceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * 验证用户是否已存在
 */
public class RegisterInterceptor implements HandlerInterceptor {
    @Autowired
    IUserService userService;

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws IOException {
        ResultBean<Boolean> resultBean = userService.isExist(request.getParameter("userName"));
        if (resultBean.getData()) {
            response.setContentType("text/html;charset=utf-8");
            response.getWriter().print(JSON.toJSON(resultBean));
            return false;
        }
        return true;
    }
}
