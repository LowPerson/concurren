package com.mmall.concurren.example;

public class package_info {
    public static void main(String[] args) {
        int i = 30;
        int n = i-1;
        n = n >>> 1;
//        n = n >>> 2;
//        n |= n >>> 4;
//        n |= n >>> 8;
//        n |= n >>> 16;
        System.out.println(n);
    }
}
