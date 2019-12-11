package com.cai.dao;

import com.cai.model.User;
import com.cai.utils.DBUtils;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class UserDao {

    private static Connection con = DBUtils.getCon();
    ;
    private static PreparedStatement pstmt;
    private static ResultSet resultSet = null;

    //用户登录
    public static boolean login(String username, String password) throws SQLException {

        String sql = "select *from user where user_name = ? and password = ?";
        pstmt = (PreparedStatement) con.prepareStatement(sql);
        pstmt.setString(1, username);
        pstmt.setString(2, password);
        resultSet = pstmt.executeQuery();
        if (resultSet.next()) {
            User u = new User();
            u.setUsername(resultSet.getString("user_name"));
            u.setPassword(resultSet.getString("password"));
            System.out.println("登录成功！");
            return true;
        } else {
            System.out.println("输入错误！");
            DBUtils.closeDB(con, pstmt, resultSet, null);
            return false;
        }
    }

    //用户注册
    public static boolean register(String username, String password) throws SQLException {

        try {
            String sql = "insert into user (user_name,password)values(?,?) ";
            pstmt = con.prepareStatement(sql);
            pstmt.setString(1, username);
            pstmt.setString(2, password);
            pstmt.executeUpdate();
            DBUtils.closeDB(con, null, resultSet, pstmt);
            return true;

        } catch (SQLException e) {
            e.printStackTrace();
        }
        DBUtils.closeDB(con, null, resultSet, pstmt);
        return false;
    }
}


