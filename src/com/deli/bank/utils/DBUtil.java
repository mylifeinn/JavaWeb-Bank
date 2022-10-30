package com.deli.bank.utils;

import java.sql.*;
import java.util.ResourceBundle;

/**
 * JDBC工具类
 *
 * @author chendeli
 * @version 1.0
 * @since 1.0
 *
 */
public class DBUtil {
    private static ResourceBundle bundle = ResourceBundle.getBundle("resources/jdbc");
    private static String driver = bundle.getString("driver");
    private static String url = bundle.getString("url");
    private static String user = bundle.getString("user");
    private static String password = bundle.getString("password");

    //构造函数私有化，不让创建对象，因为工具类中的方法都是静态的
    private DBUtil() {
    }

    //DBUtil类加载时注册驱动
    static {
        try {
            Class.forName(driver);
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    /**
     *改造开始使用Threadlocal大Map
     */
    //这个对象实际在服务器中只有一个
    private static ThreadLocal<Connection> local =new ThreadLocal<>();


    /**
     *这里没有使用数据库连接池,直接创建了连接对象
     * @return 连接对象
     * @throws SQLException
     */
    public static Connection getConnection() throws SQLException {
        //使用threadlocal
        Connection conn=local.get();
        if (conn==null) {
             conn = DriverManager.getConnection(url, user, password);
             local.set(conn);

        }
        return conn;
    }


    /**
     * 关闭资源
     * @param conn 连接对象
     * @param stmt 数据库操作对象
     * @param rs 结果集对象
     */
    public static void close(Connection conn, Statement stmt, ResultSet rs){
        if (rs != null) {
            try {
                rs.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }

        if (stmt != null) {
            try {
                stmt.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }

        if (conn != null) {
            try {
                conn.close();
                //思考以下为什么要remove,为什么要从大map中移除
                //Tomcat服务器中内置了一个线程池。
                //线程池中很多线程对象：
                //这些线程对象t1 t2 t3 都是提前创建好的。
                //也就是说t1 t2 t3存在重复使用的现象。
                local.remove();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }
}
