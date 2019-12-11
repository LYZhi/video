package com.cai.dao;

import com.cai.model.Comment;
import com.cai.utils.DBUtils;
import com.cai.utils.FoundName;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class CommentDao {

    public static boolean commentAdd (String username, Integer videoId, String comment) throws SQLException {
        int ID = VideoDao.foundId(username);
        System.out.println();
        Connection con;
        PreparedStatement pstmt = null;
        ResultSet resultSet = null;
        boolean flag=true;

        con = DBUtils.getCon();
        String sql = "insert into comment (id,video_id,comment)values(?,?,?) ";

        try {

            pstmt = con.prepareStatement(sql);
            if(videoId==null||comment==null||comment.trim().isEmpty()){
                System.out.println("评论存入失败");
                return flag = false;
            }else {
                System.out.println("评论存入成功");
                pstmt.setInt(1, ID);
                pstmt.setInt(2, videoId);
                pstmt.setString(3, comment);
                pstmt.executeUpdate();
                DBUtils.closeDB(con, null, resultSet, pstmt);
                return flag = true;

            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        DBUtils.closeDB(con, null, resultSet, pstmt);
        return flag=false;
    }

    public static List<Comment> login(Integer videoId) throws SQLException {

        PreparedStatement pstmt = null;
        ResultSet resultSet = null;

        boolean flag = true;
        Connection con = DBUtils.getCon();
        String sql = "select id,video_id,comment from comment where video_id = ?";
        pstmt = (PreparedStatement) con.prepareStatement(sql);
        pstmt.setInt(1, videoId);
        resultSet = pstmt.executeQuery();
        List<Comment> comments= new ArrayList<Comment>();
        while (resultSet.next()) {
            Comment comment = new Comment();
            comment.setComment(resultSet.getString("comment"));
            String userName = FoundName.foundUserName(resultSet.getInt("id"));
            System.out.println("此评论的用户id："+userName);
            comment.setCommentUserName(userName);
            comments.add(comment);
        }
//        List<String> name = comments.stream().map(Comment::getComment).collect(Collectors.toList());
//        System.out.println("shuchu=========== "+ name);
        DBUtils.closeDB(con, pstmt, resultSet, null);
        return comments;
    }

}
