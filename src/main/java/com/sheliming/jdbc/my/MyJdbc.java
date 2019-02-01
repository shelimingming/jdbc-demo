package com.sheliming.jdbc.my;

import java.sql.Connection;
import java.sql.Driver;
import java.util.Properties;

public class MyJdbc {
    public static void driverTest() {
        try {
            //1.加载mysql驱动类，并实例化
            Driver driver = (Driver) Class.forName("com.mysql.jdbc.Driver").newInstance();
            //甚至可以直接这样写
            //Driver driver = new com.mysql.jdbc.Driver();

            //2.判定指定的URL mysql驱动能否接受
            boolean flag = driver.acceptsURL("jdbc:mysql://localhost:3306/test?serverTimezone=UTC");
            System.out.println("协议测试：" + flag);

            //3.创建真实的数据库连接：
            String url = "jdbc:mysql://localhost:3306/test?serverTimezone=UTC";
            Properties props = new Properties();
            props.put("user", "root");
            props.put("password", "123456");
            Connection connection = driver.connect(url, props);

            System.out.println(connection);

        } catch (Exception e) {
            System.out.println("加载mysql类失败！");
            e.printStackTrace();
        } finally {

        }
    }

    public static void main(String[] args) {
        driverTest();
    }
}
