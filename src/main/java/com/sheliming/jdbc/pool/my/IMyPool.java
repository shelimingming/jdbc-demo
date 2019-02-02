package com.sheliming.jdbc.pool.my;

public interface IMyPool {
    MyPooledConnection getMyPooledConnection();

    void createMyPoolConnection(int count);
}
