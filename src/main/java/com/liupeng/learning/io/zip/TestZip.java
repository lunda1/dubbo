package com.liupeng.learning.io.zip;

import java.io.*;
import java.util.zip.ZipEntry;
import java.util.zip.ZipException;
import java.util.zip.ZipFile;
import java.util.zip.ZipInputStream;

public class TestZip {
    public static void main(String[] args) throws IOException, ZipException {
        testReadZip();
    }

    public static void testReadZip() throws IOException, ZipException {
        File file = new File("src/main/resources/io/HAOQIAO_test_static_xml.zip");
        ZipFile zipFile = new ZipFile(file);
        ZipInputStream zipIn = new ZipInputStream(new FileInputStream(file));

        ZipEntry zipEn = null;
        while ((zipEn = zipIn.getNextEntry()) != null) {
            if (!zipEn.isDirectory()) {
                System.out.println(zipEn.getName() + ";\t");
                File outFile = new File ( "src/main/resources/io/tmp/" + zipEn.getName() ) ;
                if (!outFile.getParentFile().exists()){
                    outFile.getParentFile().mkdirs();
                }
                if (!outFile.exists()) {
                    outFile.createNewFile();
                }

                BufferedInputStream bis = null;
                BufferedOutputStream bos = null;
                bis = new BufferedInputStream(zipFile.getInputStream(zipEn));
                bos = new BufferedOutputStream(new FileOutputStream(outFile));
                byte[] bytes = new byte[100];
                int len = bis.read(bytes);
                while (len != -1) {
                    bos.write(bytes, 0, len);
                    len = bis.read(bytes);
                }
                bis.close();
                bos.close();
            }
            //System.out.println(zipEn.getName() + ";\t");
        }
        zipIn.close();
    }
}
