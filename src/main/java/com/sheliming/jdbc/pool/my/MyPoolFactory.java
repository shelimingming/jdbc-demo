package com.sheliming.jdbc.pool.my;

public class MyPoolFactory {
    public static class CreatePool {
        public static IMyPool myPool = new MyDefaultPool();
    }

    public static IMyPool getInstance() {
        return CreatePool.myPool;
    }
}
