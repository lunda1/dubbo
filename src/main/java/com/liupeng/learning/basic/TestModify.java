package com.liupeng.learning.basic;

import java.io.*;

/**
 * @Author oliver.liu
 * @Date 2019/12/5 18:54
 */
public class TestModify {
    public static void main(String[] args) throws IOException {
        File f1 = new File("D:\\MyConfiguration\\oliver.liu\\Desktop\\modify\\in-1127-1130.txt");
        FileInputStream fis = new FileInputStream(f1);
        BufferedReader br = new BufferedReader(new InputStreamReader(fis));

        File f2 = new File("D:\\MyConfiguration\\oliver.liu\\Desktop\\modify\\out-1127-1130.txt");
        FileOutputStream fos = new FileOutputStream(f2);
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(fos));

        String s;
        int i=1;
        while ((s = br.readLine()) != null) {
            String seq = String.valueOf(i).length()==1?"00"+i:String.valueOf(i).length()==2?"0"+i:String.valueOf(i);
            System.out.print(seq+" ");
            String updateStr = "update vehicle_info set color='"+getColor(s)+"',brand='"+getBrand(s)+"' where create_time>'2019-11-27 00:00:00' and vehicle_no='"+getVehicleNo(s)+"';";
            System.out.println(updateStr);
            bw.write(updateStr+"\n");
            i++;
        }
        br.close();
        fis.close();

        bw.close();
        fos.close();
    }

    private static String getVehicleNo(String s){
        String vehicleNoStart = "\"vehicleNo\":\"";
        String vehicleNoEnd = "\"}";
        int indexOfVehicleNoStart = s.indexOf(vehicleNoStart);
        int indexOfVehicleNoEnd = s.indexOf(vehicleNoEnd);
        String vehicleNo = s.substring(indexOfVehicleNoStart+vehicleNoStart.length(),indexOfVehicleNoEnd);
        return vehicleNo;
    }

    private static String getColor(String s){
        String colorStart = "\"color\":\"";
        String colorEnd = "\",\"driveIdCard\"";
        int indexOfcolorStart = s.indexOf(colorStart);
        int indexOfcolorEnd = s.indexOf(colorEnd);
        String color = s.substring(indexOfcolorStart+colorStart.length(),indexOfcolorEnd);
        return color;
    }

    private static String getBrand(String s){
        String brandStart = "\"brand\":\"";
        String brandEnd = "\",\"color\"";
        int indexOfbrandStart = s.indexOf(brandStart);
        int indexOfbrandEnd = s.indexOf(brandEnd);
        String brand = s.substring(indexOfbrandStart+brandStart.length(),indexOfbrandEnd);
        return brand;
    }
}
