package com.sheliming.jdbc.pool.my;

/**
 * 模拟从xml中读取配置文件
 */
public class DBConfigXml {
    public static final String jdbcDriver = "com.mysql.jdbc.Driver";
    public static final String url = "jdbc:mysql://localhost:3306/test?serverTimezone=UTC";
    public static final String user = "root";
    public static final String password = "123456";

    //线程池初始化的个数
    public static final int initCount = 10;
    //线程池最大的个数
    public static final int maxCount = 20;
    //线程池不足时每次增加的个数
    public static final int step = 2;

}
