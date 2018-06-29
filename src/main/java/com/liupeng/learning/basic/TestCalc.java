package com.liupeng.learning.basic;

import java.math.BigDecimal;

public class TestCalc {
    public static void main(String[] args) {
        //System.out.println(new Integer(String.valueOf(2*0.95)));
        System.out.println(new BigDecimal(2).multiply(new BigDecimal(0.95)).setScale(0,BigDecimal.ROUND_HALF_UP));
    }
}
