package com.liupeng.learning.io.ftp;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;

public class TestFTP {
    public static String ftpHost = "100.80.146.145";
    public static String ftpUserName = "ftptest";
    public static String ftpPassword = "1qaz2WSX";
    public static int ftpPort = 21;
    public static String ftpPath = "";
    public static String localPath = "c:\\ftpdownload";
    public static String fileName = "ftptestfile1.txt";

    public static void main(String[] args) {
//        download();
        upload();
    }

    public static void download() {
        //从ftp服务器上下载同名文件到本地
        FtpUtil.downloadFtpFile(ftpHost,ftpUserName,ftpPassword,ftpPort,ftpPath, localPath, fileName);
    }

    public static void upload(){
        //上传一个文件
        try{
            FileInputStream in = new FileInputStream(new File("c://ftpdownload/ftptestfile1.txt"));
            boolean test = FtpUtil.uploadFile(ftpHost, ftpUserName, ftpPassword, ftpPort, ftpPath, "ftptestfile1.txt", in);
            System.out.println(test);
        } catch (FileNotFoundException e){
            e.printStackTrace();
        }
    }
}
