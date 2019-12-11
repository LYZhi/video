package com.cai.dao;

import com.cai.model.Video;
import com.cai.utils.DBUtils;
import com.cai.utils.FoundName;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.*;

public class VideoDao {
    private static Connection con = DBUtils.getCon();;
    private static PreparedStatement pstmt ;
    private static ResultSet resultSet = null;
    private  static boolean flag=true;

    public static boolean upload(String username, String videoName, String videoUrl) throws SQLException {

        int Id = foundId(username);
        System.out.println("用户id" + Id);
        String sql = "insert into video (id,video_name,video_url)values(?,?,?) ";

        try {

            pstmt = con.prepareStatement(sql);
            if (videoName == null || videoName.trim().isEmpty() || videoUrl == null || videoUrl.trim().isEmpty()) {
                System.out.println("视频存入数据失败");
                return flag = false;
            } else {
                System.out.println("视频存入数据成功");
                pstmt.setInt(1, Id);
                pstmt.setString(2, videoName);
                pstmt.setString(3, videoUrl);
                pstmt.executeUpdate();

                return flag = true;

            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return flag;
    }

    public static int foundId(String username) throws SQLException {
        System.out.println("传入的用户名" + username);
        String sql = "select id,user_name from user where user_name = ? ";
        pstmt = (PreparedStatement) con.prepareStatement(sql);
        pstmt.setString(1, username);
        resultSet = pstmt.executeQuery();
        if (resultSet.next()) {

            if (resultSet.getString("user_name").equals(username)) {
                return resultSet.getInt("id");
            }

        } else {
            System.out.println("输入错误！");
            return -1;
        }
        return -1;
    }

    public static List<Video> login() throws SQLException {

        String sql = "select id,video_id,video_name from video ";
        pstmt = (PreparedStatement) con.prepareStatement(sql);
        resultSet = pstmt.executeQuery(sql);
        List<Video> videos = new ArrayList<Video>();
        while (resultSet.next()) {
            Video vid = new Video();
            vid.setVideoId(resultSet.getInt("video_id"));
            vid.setVideoName(resultSet.getString("video_name"));
            String id = FoundName.foundUserName(resultSet.getInt("id"));
            vid.setVideoUserName(id);
            videos.add(vid);
        }

        return videos;
    }

    public static String foundVideoUrl(Integer videoId) throws SQLException {

        System.out.println("传入视频id"+videoId);
        String sql = "select video_id,video_url from video where video_id = ? ";
        pstmt = (PreparedStatement) con.prepareStatement(sql);
        pstmt.setInt(1, videoId);
        resultSet = pstmt.executeQuery();
        if (resultSet.next()) {

            if (resultSet.getInt("video_id")==videoId) {
                return resultSet.getString("video_url");
            }

        } else {
            System.out.println("输入错误！");

        }
        return null;
    }

}
