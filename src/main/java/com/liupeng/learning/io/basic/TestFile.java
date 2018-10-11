package com.liupeng.learning.io.basic;

import java.io.File;
import java.io.FileWriter;

public class TestFile {
    public static void main(String[] args) {
        try {
            File file = new File("d://today.log");

            File file2 = new File("d://yesterday.log");
            //会将today.log文件改名为yesterday.log文件
            file.renameTo(file2);

            FileWriter fw = new FileWriter(file,true);
            try {
                fw.write("nnn");
                fw.flush();
            } catch (Exception e) {
                e.printStackTrace();
            } finally {
                fw.close();
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
