package com.cai.controller;

import com.cai.dao.UserDao;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.*;

public class RegisterServlet extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("UTF-8");

        String username = request.getParameter("username");
        String password = request.getParameter("password");
        System.out.println(username);
        int passwordLength = 6;
        if(username==null||username.trim().isEmpty()||password==null
                ||password.trim().isEmpty()||password.length()<passwordLength){
            request.setAttribute("msg","您的输入有误，请重新输入");
            request.getRequestDispatcher("/WEB-INF/register.jsp").forward(request, response);
        }else if("**".equals(username)){
            request.setAttribute("msg","您的输入有敏感词，请重新输入");
            request.getRequestDispatcher("/WEB-INF/register.jsp").forward(request, response);
        }else {
            try {
                boolean flag = UserDao.register(username,password);
                if(!flag){
                    request.setAttribute("msg","您的输入有误，请重新输入");
                    request.getRequestDispatcher("/WEB-INF/register.jsp").forward(request, response);
                }else {
                    //注册成功
                    request.setAttribute("msg","注册成功！请登录");
                    request.getRequestDispatcher("index.jsp").forward(request, response);
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }

    }
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.getRequestDispatcher("WEB-INF/register.jsp").forward(request, response);

    }

}
