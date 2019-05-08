package com.mad.trafficclient.st_java.test;

public class Demo {
    //瓶子
    static int i = 0;
    //盖子
    static int j = 0;
    //喝到肚子里的
    static int m = 0;
    //金钱
    static int money = 10;

    public static void main(String[] args) {
        maimaimai();
        fun1();
    }

    private static void maimaimai() {
        while ((money -= 2) >= 0) {
            m += 1;
            i += 1;
            j += 1;
            System.out.println(m + "");
        }
    }

    //用瓶子换
    private static void fun2() {
        while ((i -= 2) >= 0) {
            m += 1;
            i += 1;
            j += 1;
            System.out.println(m + "");
        }
        if (j >= 4) {
            fun1();
        }
    }

    //用盖子换
    private static void fun1() {
        while ((j -= 4) >= 0) {
            m += 1;
            i += 1;
            j += 1;
            System.out.println(m + "");
        }
        if (i >= 2) {
            fun2();
        }
    }
}
