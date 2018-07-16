package com.liupeng.learning.basic;

import java.math.BigDecimal;

public class TestBigDecimal {
    public static void main(String[] args) {
        test2();
    }

    public static void test1(){
        BigDecimal amount = new BigDecimal(0.5);
        double finalAmt = amount.divide(new BigDecimal(3),10,BigDecimal.ROUND_HALF_UP).divide(new BigDecimal(3),2,BigDecimal.ROUND_HALF_UP).doubleValue();
        //Integer finalAmt = amount.divide(new BigDecimal(3),2,BigDecimal.ROUND_HALF_UP).divide(new BigDecimal(3)).setScale(2,BigDecimal.ROUND_HALF_UP).intValue();
        System.out.println(finalAmt);

        BigDecimal a2 = new BigDecimal(5.78).setScale(0,BigDecimal.ROUND_FLOOR);
        System.out.println(a2);
    }

    public static void test2(){

        BigDecimal a3 = new BigDecimal(5.78).setScale(0,BigDecimal.ROUND_UP);
        System.out.println(a3.intValue());

        BigDecimal a1 = new BigDecimal(5.78).setScale(0,BigDecimal.ROUND_HALF_UP);
        System.out.println(a1.intValue());

        BigDecimal a2 = new BigDecimal(5.78);
        System.out.println(a2.intValue());
    }
}
