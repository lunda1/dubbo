package com.liupeng.learning.algorithm.dynamic;

/**
 * 走台阶问题，每次一步或者两步
 * @Author oliver.liu
 * @Date 2019/9/12 18:56
 * https://juejin.im/post/5a29d52cf265da43333e4da7
 * https://blog.csdn.net/qq_37763204/article/details/79394397
 */
public class FibonacciTest {
    public static void main(String[] args) {
        calculate(8);
    }


    public static void calculate(int n){
        if (n < 0) {
            return;
        } else if (n == 1) {
            System.out.println("1");
            return;
        } else if (n == 2) {
            System.out.println("2");
            return;
        }

        int a = 1;
        int b = 2;
        int tmp = 0;
        for (int i=3;i<=n;i++) {
            tmp = a + b;
            a = b;
            b = tmp;
        }
        System.out.println(tmp);
        return;
    }
}
