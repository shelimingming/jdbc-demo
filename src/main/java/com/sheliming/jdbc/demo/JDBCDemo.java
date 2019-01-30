package com.sheliming.jdbc.demo;

import java.sql.*;

/**
 * jdbc使用demo
 */
public class JDBCDemo {
    public static void main(String[] args) {
        Connection conn = null;// 数据库连接对象
        Statement stmt = null;// 数据库操作对象
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
            System.out.println(conn);

            //3.获取数据库操作对象
            stmt = conn.createStatement();
            System.out.println(stmt);

            //4.执行SQL语句之DML语句
            String sql = "select * from user";
            rs = stmt.executeQuery(sql);
            System.out.println(rs);

            /*
			+----+--------+------+------------------+
			| id | name   | sex  | email            |
			+----+--------+------+------------------+
			 */
            //5、处理查询结果集
            while (rs.next()) {
                int id = rs.getInt("id");
                String name = rs.getString("name");
                String sex = rs.getString("sex");
                String email = rs.getString("email");
                System.out.println("编号" + id + "\t" + "姓名" + name + "\t" + "性别" + sex + "\t" + "邮箱" + email);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } finally {
            // 6、关闭资源 倒序依次关闭
            //6.1 当查询结果集不为空时，先关闭结果集对象
            if (rs != null) {
                try {
                    rs.close();
                } catch (SQLException e) {
                    // TODO Auto-generated catch block
                    e.printStackTrace();
                }
            }
            //6.2 当数据库操作对象不为空时，再关闭数据库操作对象
            if (stmt != null) {
                try {
                    stmt.close();
                } catch (SQLException e) {
                    // TODO Auto-generated catch block
                    e.printStackTrace();
                }
            }
            //6.3 当数据库连接对象不为空时，最后关闭数据库连接对象
            if (conn != null) {
                try {
                    conn.close();
                } catch (SQLException e) {
                    // TODO Auto-generated catch block
                    e.printStackTrace();
                }
            }
        }

    }
}
