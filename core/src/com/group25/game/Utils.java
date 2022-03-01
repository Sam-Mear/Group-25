package com.group25.game;

public class Utils {

    public static long factorial(int x){
        long total = 1;
        for(int i=1;i<=x;i++){
            total = total*i;
        }
        return total;
    }
}
