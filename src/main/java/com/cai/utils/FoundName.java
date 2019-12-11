package com.cai.utils;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class FoundName {
    private static Connection con = DBUtils.getCon();;
    private static PreparedStatement pstmt ;
    private static ResultSet resultSet = null;

    public static String foundUserName(Integer id) throws SQLException {

        String sql = "select id,user_name from user where id = ? ";
        pstmt = (PreparedStatement) con.prepareStatement(sql);
        pstmt.setInt(1, id);
        resultSet = pstmt.executeQuery();
        if (resultSet.next()) {
            int temp = resultSet.getInt("id");
            System.out.println("用户id查找："+temp);
            if (temp==id) {
                System.out.println("匹配成功");
                return resultSet.getString("user_name");
            }
        } else {
            System.out.println("输入错误！");
            return null;
        }
        return null;
    }
    public static String foundVideoName(Integer id) throws SQLException {

        String sql = "select video_id,video_name from video where video_id = ? ";
        pstmt = (PreparedStatement) con.prepareStatement(sql);
        System.out.println(id);
        pstmt.setInt(1, id);
        resultSet = pstmt.executeQuery();
        if (resultSet.next()) {
            int temp = resultSet.getInt("video_id");
            System.out.println("视频id查找："+temp);
            if (temp==id) {
                System.out.println("匹配成功");
                return resultSet.getString("video_name");
            }
        } else {
            System.out.println("输入错误！");
            return null;
        }
        return null;
    }
}
