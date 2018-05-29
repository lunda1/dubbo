package com.liupeng.learning.haoqjob;

import com.alibaba.fastjson.JSON;

import java.io.*;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.concurrent.*;
import java.util.zip.ZipEntry;
import java.util.zip.ZipException;
import java.util.zip.ZipFile;
import java.util.zip.ZipInputStream;

public class TestHaoQJob {
    public static void main(String[] args) throws IOException {
        BlockingQueue queue = getHotelTaskQueue();
        if (queue!=null && !queue.isEmpty()) {
            //增加校验数据文件的个数来粗略的判断数据是否有问题
            updateHotel(queue);
        }
    }

    public static BlockingQueue getHotelTaskQueue() throws IOException, ZipException {
        File file = new File("src/main/resources/io/HAOQIAO_test_static_xml.zip");
        ZipFile zipFile = new ZipFile(file);
        ZipInputStream zipIn = new ZipInputStream(new FileInputStream(file));
        BlockingQueue queue = new LinkedBlockingDeque();
        ZipEntry zipEn = null;
        while ((zipEn = zipIn.getNextEntry()) != null) {
            if (!zipEn.isDirectory()) {
                //打印压缩文件名称
                //System.out.println(zipEn.getName() + ";\t");

                File outFile = new File ( "src/main/resources/io/tmp/" + zipEn.getName() ) ;
                if (!outFile.getParentFile().exists()){
                    outFile.getParentFile().mkdirs();
                }
                if (!outFile.exists()) {
                    outFile.createNewFile();
                }

                queue.offer(outFile.getName());
                //打印文件名称
                //System.out.println(outFile.getName());

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
        System.out.println("hotel task queue: "+JSON.toJSONString(queue));
        return queue;
    }

    public static void updateHotel(BlockingQueue<String> queue) {

        ExecutorService pool = Executors.newFixedThreadPool(5);
        for (int i=0; i<5; i++) {
            pool.submit(new Runnable() {
                @Override public void run() {
                    String fileName = null;
                    try {
                        while ((fileName = queue.poll()) != null) {
                            TimeUnit.SECONDS.sleep(3);
                            //插入数据
                            SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");//设置日期格式
                            System.out.println("insert data: "+fileName+" "+df.format(new Date()));
                        }
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            });
        }
        pool.shutdown();
    }
}
