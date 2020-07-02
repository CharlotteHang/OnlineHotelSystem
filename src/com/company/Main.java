package com.company;

public class Main {

    public static void main(String[] args) {
        // write your code here
        split();

    }


    static void split(){
        String s= "ss,sdad,asdasd";
        String[]ss =s.split("\\,");
        System.out.println(ss[0]);

    }
}