package com.cai.utils;

import java.sql.*;

public class DBUtils {
    private static final String driverClass;
    private static final String url;
    private static final String username;
    private static final String password;

    static {


        driverClass = "com.mysql.jdbc.Driver";
        url = "jdbc:mysql://localhost:3307/try";
        username = "root";
        password = "asdfghjkl";
    }

    public static Connection getCon() {
        Connection conn = null;
        Statement stmt = null;
        try {
            //注册驱动
            Class.forName(driverClass);
            //获得连接
            conn = DriverManager.getConnection(url, username, password);
            System.out.println("数据库连接成功！");

        } catch (Exception e) {
            e.printStackTrace();
        }
        return conn;
    }

    //资源释放
    public static void closeDB(Connection con, PreparedStatement pstt,
                               ResultSet res, Statement stmt)  {
        if (con != null) {
            try {
                con.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
            con = null;
        }
        if (pstt != null) {
            try {
                pstt.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
            pstt = null;
        }
        if (res != null) {
            try {
                res.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
            res = null;
        }
        if (stmt != null) {
            try {
                stmt.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
            stmt = null;
        }
    }


}
