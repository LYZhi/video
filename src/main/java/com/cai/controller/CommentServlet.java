package com.cai.controller;

import com.cai.dao.CommentDao;
import com.cai.dao.VideoDao;
import com.cai.model.Comment;
import com.cai.utils.FoundName;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.SQLException;
import java.util.List;


@WebServlet("/comment.jsp")
public class CommentServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        response.setContentType("text/html;charset=UTF-8");
        request.setCharacterEncoding("UTF-8");
        try {

            Integer loginUserId;
            if (request.getParameter("videoId") == null) {
                Integer id = (Integer) request.getServletContext().getAttribute("videoId");
                loginUserId = id;
            } else {
                loginUserId = Integer.parseInt(request.getParameter("videoId"));
            }

            request.getSession().setAttribute("videoId", loginUserId);
            String videoName = FoundName.foundVideoName(loginUserId);
            request.getSession().setAttribute("videoName", videoName);

            String videoUrl = VideoDao.foundVideoUrl(loginUserId);
            request.setAttribute("src", videoUrl);

            //分页
            String pageStr = request.getParameter("page");
            int page = 1;
            if (null != pageStr && !"".equals(pageStr)) {
                page = Integer.parseInt(pageStr);
            }
            List comments = CommentDao.login(loginUserId);

            int totalComments = comments.size();
            int totalPage = totalComments % 6 > 0 ? totalComments / 6 + 1 : totalComments / 6;

            request.setAttribute("curPage", page);
            request.setAttribute("prePage", page > 1 ? page - 1 : 1);
            request.setAttribute("nextPage", totalPage > page ? page + 1 : totalPage);
            request.setAttribute("totalPage", totalPage);
            request.setAttribute("comments", Comment.getComments(page, 6, comments));

            request.getRequestDispatcher("WEB-INF/comment.jsp").forward(request, response);
        } catch (SQLException e) {
            e.printStackTrace();
        }

    }

}
