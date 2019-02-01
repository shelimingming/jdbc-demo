package com.sheliming.jdbc.demo;

import java.sql.*;

/**
 * jdbc实现事务的demo
 */
public class JDBCTransactionDemo {
    public static void main(String[] args) throws SQLException {
        Connection conn = null;// 数据库连接对象
        PreparedStatement pstmt = null;// 数据库操作对象
        ResultSet rs = null;// 查询结果集对象
        try {
            //1.注册驱动
            Class.forName("com.mysql.jdbc.Driver");
            //手动注册
            //Driver driver = new com.mysql.jdbc.Driver();
            //DriverManager.registerDriver(driver);

            //2.获取数据库连接
            String url = "jdbc:mysql://localhost:3306/test?serverTimezone=UTC";
            String user = "root";
            String password = "123456";
            conn = DriverManager.getConnection(url, user, password);
            //************设置连接不进行自动提交***********
            conn.setAutoCommit(false);
            System.out.println(conn);

            //3.获取数据库操作对象
            pstmt = conn.prepareStatement("insert into user(name,sex,email) values (?,?,?)");
            System.out.println(pstmt);
            //4.执行SQL语句之DML语句
            pstmt.setString(1, "sheliming");
            pstmt.setString(2, "男");
            pstmt.setString(3, "sheliming@qq.com");
            boolean result = pstmt.execute();
            System.out.println(result);

            int i = 1 / 0;

            //3.获取数据库操作对象
            pstmt = conn.prepareStatement("insert into user(name,sex,email) values (?,?,?)");
            System.out.println(pstmt);
            //4.执行SQL语句之DML语句
            pstmt.setString(1, "sheliming2");
            pstmt.setString(2, "男");
            pstmt.setString(3, "sheliming2@qq.com");
            boolean result2 = pstmt.execute();
            System.out.println(result2);

            conn.commit();

            /*
			+----+--------+------+------------------+
			| id | name   | sex  | email            |
			+----+--------+------+------------------+
			 */

        } catch (SQLException e) {
            try {
                conn.rollback();
            } catch (SQLException e1) {
                e1.printStackTrace();
            }
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            try {
                conn.rollback();
            } catch (SQLException e1) {
                e1.printStackTrace();
            }
            e.printStackTrace();
        } finally {
            // 6、关闭资源 倒序依次关闭
            //6.1 当查询结果集不为空时，先关闭结果集对象
            if (rs != null) {
                try {
                    rs.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
            //6.2 当数据库操作对象不为空时，再关闭数据库操作对象
            if (pstmt != null) {
                try {
                    pstmt.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
            //6.3 当数据库连接对象不为空时，最后关闭数据库连接对象
            if (conn != null) {
                try {
                    conn.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
        }
    }
}
