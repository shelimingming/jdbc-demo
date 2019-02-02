package com.sheliming.jdbc.pool.my;

public class Test {
    public static void main(String[] args) {
        IMyPool myPool = MyPoolFactory.getInstance();

        for (int i = 0; i < 100; i++) {
            MyPooledConnection myPooledConnection = myPool.getMyPooledConnection();
            System.out.println(myPooledConnection);
            myPooledConnection.close();
        }

    }
}
