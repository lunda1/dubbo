package com.liupeng.learning.basic;

import java.math.BigDecimal;

public class TestBigDecimal {
    public static void main(String[] args) {
        BigDecimal amount = new BigDecimal(0.5);
        double finalAmt = amount.divide(new BigDecimal(3),10,BigDecimal.ROUND_HALF_UP).divide(new BigDecimal(3),2,BigDecimal.ROUND_HALF_UP).doubleValue();
        //Integer finalAmt = amount.divide(new BigDecimal(3),2,BigDecimal.ROUND_HALF_UP).divide(new BigDecimal(3)).setScale(2,BigDecimal.ROUND_HALF_UP).intValue();
        System.out.println(finalAmt);

    }
}
