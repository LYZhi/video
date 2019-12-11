package com.cai.controller;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class LogoutServlet extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException {
        //销毁session
        request.getSession().invalidate();
        //删除cookie
        Cookie cookie = new Cookie("userName",null);
        //设置serMaxAge(0)
        cookie.setMaxAge(0);
        //响应
        response.addCookie(cookie);
        //跳转页面
        response.sendRedirect("index.jsp");

    }
}
