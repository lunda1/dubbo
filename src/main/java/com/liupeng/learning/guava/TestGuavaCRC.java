package com.liupeng.learning.guava;

import com.google.common.hash.Hashing;

public class TestGuavaCRC {
    public static void main(String[] args) {
        String input = "hello, world!";
        // 计算MD5
        System.out.println(Hashing.md5().hashBytes(input.getBytes()).toString());
        // 计算sha256
        System.out.println(Hashing.sha256().hashBytes(input.getBytes()).toString());
        // 计算sha512
        System.out.println(Hashing.sha512().hashBytes(input.getBytes()).toString());
        // 计算crc32
        System.out.println(Hashing.crc32().hashBytes(input.getBytes()).toString());
        System.out.println(Hashing.crc32().hashBytes(input.getBytes()).asBytes().length);
    }
}
