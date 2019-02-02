package com.sheliming.jdbc.pool.my;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Vector;

public class MyDefaultPool implements IMyPool {

    private String jdbcDriver;
    private String url;
    private String user;
    private String password;

    //线程池初始化的个数
    private int initCount;
    //线程池最大的个数
    private int maxCount;
    //线程池不足时每次增加的个数
    private int step;

    private Vector<MyPooledConnection> myPooledConnectionVector = new Vector<MyPooledConnection>();

    public MyDefaultPool() {
        init();

        //加载数据库驱动
        try {
            Class.forName(jdbcDriver);
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }

        createMyPoolConnection(initCount);
    }

    private void init() {
        jdbcDriver = DBConfigXml.jdbcDriver;
        url = DBConfigXml.url;
        user = DBConfigXml.user;
        password = DBConfigXml.password;
        initCount = DBConfigXml.initCount;
        maxCount = DBConfigXml.maxCount;
        step = DBConfigXml.step;
    }

    @Override
    public MyPooledConnection getMyPooledConnection() {
        MyPooledConnection myPooledConnection = null;

        myPooledConnection = getRealConnectionFromPool();
        while(myPooledConnection == null){
            createMyPoolConnection(step);
            myPooledConnection = getRealConnectionFromPool();
        }

        return myPooledConnection;
    }

    private synchronized MyPooledConnection getRealConnectionFromPool() {
        for (MyPooledConnection myPooledConnection : myPooledConnectionVector) {
            if (!myPooledConnection.isBusy()) {
                myPooledConnection.setBusy(true);
                return myPooledConnection;
            }
        }
        return null;
    }

    @Override
    public void createMyPoolConnection(int count) {
        if (myPooledConnectionVector.size() + count > maxCount) {
            throw new RuntimeException("连接池已满!");
        }
        try {
            Connection connection = DriverManager.getConnection(url, user, password);
            MyPooledConnection myPooledConnection = new MyPooledConnection(connection, false);
            myPooledConnectionVector.add(myPooledConnection);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
