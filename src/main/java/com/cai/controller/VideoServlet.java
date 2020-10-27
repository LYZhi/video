package com.cai.controller;

import com.cai.dao.VideoDao;
import com.cai.model.Video;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.SQLException;
import java.util.List;


@WebServlet("/show.jsp")
public class VideoServlet extends HttpServlet {

    @Override
    protected  void doGet(HttpServletRequest request, HttpServletResponse response) {

        try {
            //分页
            String pageStr = request.getParameter("page");
            int page = 1;
            if (null != pageStr && !"".equals(pageStr)) {
                page = Integer.parseInt(pageStr);
            }
            List videos = VideoDao.login();
            int totalVideos = videos.size();
            int totalPage=0;
            if(totalVideos==0){
                totalPage=1;
            }else {
                totalPage = totalVideos % 6 > 0 ? totalVideos / 6 + 1 : totalVideos / 6;
            }

            request.setAttribute("curPage", page);
            request.setAttribute("prePage", page > 1 ? page - 1 : 1);
            request.setAttribute("nextPage", totalPage > page ? page + 1 : totalPage);
            request.setAttribute("totalPage", totalPage);
            request.setAttribute("videos", Video.getVideos(page, 6, videos));

            request.getRequestDispatcher("WEB-INF/show.jsp").forward(request, response);
        } catch (SQLException | ServletException | IOException e) {
            e.printStackTrace();
        }

    }
}
