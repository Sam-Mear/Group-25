package com.group25.game;

public class Utils {

    /**
     * This is used to calculate the factorials of numbers
     * @param x What is the factorial we want to workout
     * @return
     */

    public static long factorial(int x){
        long total = 1;
        for(int i=1;i<=x;i++){
            total = total*i;
        }
        return total;
    }
}
