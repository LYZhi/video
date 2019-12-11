package com.cai.controller;

import com.cai.dao.CommentDao;
import com.cai.utils.CookieUtils;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.SQLException;

public class NewCommentServlet extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws IOException {
        response.setContentType("text/html;charset=UTF-8");
        request.setCharacterEncoding("UTF-8");

        Cookie[] cookies = request.getCookies();
        Cookie cookie = CookieUtils.findCookie(cookies, "username");

        assert cookie != null;
        String username = cookie.getValue();

        System.out.println("这是个啥video值？"+request.getParameter("videoId"));
        Integer  videoId = Integer.parseInt(request.getParameter("videoId"));
        try {
//            System.out.println("用户名"+username+"视频ID"+videoId+"评论"+request.getParameter("comment1"));
            if(CommentDao.commentAdd(username, videoId, request.getParameter("comment1"))){
                request.getServletContext().setAttribute("videoId",videoId);
                response.sendRedirect("CommentServlet");

            }else{
                System.out.println("出错了！");
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

    }
}
