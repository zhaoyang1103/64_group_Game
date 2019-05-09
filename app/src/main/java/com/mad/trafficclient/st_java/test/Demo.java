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
        maimaimai();//先买再换
//        mai();//能换就换
    }


    //先买再换
    private static void maimaimai() {
        while ((money -= 2) >= 0) {
            m += 1;
            i += 1;
            j += 1;
            System.out.println("花钱买喝到的" + m + "");
            System.out.println("瓶子= " + i + ",盖子=" + j);
        }
        fun1();
        money += 2;
        if (money <= 0) {
            money = 0;
        }
        System.out.println("剩下的钱" + money + "");
    }

    //能换就换
    private static void mai() {
        while ((money -= 2) >= 0) {
            m += 1;
            i += 1;
            j += 1;
            System.out.println("花钱买喝到的" + m + "");
            System.out.println("瓶子= " + i + ",盖子=" + j);
            fun1();
        }
        money += 2;
        if (money <= 0) {
            money = 0;
        }
        System.out.println("剩下的钱" + money + "");
    }

    //用瓶子换
    private static void fun2() {
        while ((i -= 2) >= 0) {
            m += 1;
            i += 1;
            j += 1;
            System.out.println("用空瓶子换" + m + "");
            System.out.println("瓶子= " + i + ",盖子=" + j);
        }
        i += 2;
        if (i < 0) {
            i = 0;
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
            System.out.println("用盖子换" + m + "");
            System.out.println("瓶子= " + i + ",盖子=" + j);
        }
        j += 4;
        if (j < 0) {
            j = 0;
        }
        if (i >= 2) {
            fun2();
        }
    }
}
