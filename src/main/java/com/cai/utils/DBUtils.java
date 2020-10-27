package com.cai.utils;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.io.InputStream;
import java.sql.*;
import java.util.Properties;

public class DBUtils {
    private static String driverClass;
    private static String url;
    private static String username;
    private static String password;

    static {
        try {
            // 1.通过当前类获取类加载器
            ClassLoader classLoader = DBUtils.class.getClassLoader();
            // 2.通过类加载器的方法获得一个输入流
            InputStream in = classLoader.getResourceAsStream("test.properties");
            // 3.创建一个properties对象(集合)
            Properties props = new Properties();
            // 4.加载输入流
            props.load(in);
            // 5.获取相关参数的值
            driverClass = props.getProperty("driverClass");
            url = props.getProperty("url");
            username = props.getProperty("username");
            password = props.getProperty("password");

        } catch (IOException e) {
            e.printStackTrace();
        }
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
                               ResultSet res, Statement stmt) {
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
