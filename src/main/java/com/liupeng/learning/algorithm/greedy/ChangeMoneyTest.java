package com.liupeng.learning.algorithm.greedy;

/**
 * @Author oliver.liu
 * @Date 2019/8/28 12:44
 */
public class ChangeMoneyTest {

    public static int[] value = {1,2,5,10,20,50,100};

    public static int[] count = {3,0,2,1,0,3,5};

    public static void main(String[] args) {
        System.out.println("sum: "+ change(328));
    }

    public static int change(int money){
        int sum = 0;

        for (int i=count.length-1;i>=0;i--) {
            int c = Math.min(money/value[i],count[i]);
            money -= value[i]*c;
            sum += c;
        }

        if (money != 0) {
            System.out.println("money left: "+money);
        }
        return sum;
    }
}
