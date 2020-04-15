package com.clh.iot.networkService.util;

import org.springframework.core.io.ClassPathResource;

import java.io.*;
import java.math.BigDecimal;
import java.util.Map;
import java.util.Properties;

public class ClhUtils {
    public static void main(String[] args) {



    }

        static{

        }
    /**
     * 加载配置文件
     * @param filename
     * @return
     */
    public  Properties loadProperties(String filename){
        InputStream inputStream ;
        try {
            //resource 目录下的资源必须以这种方式进行读取。打包编译后在classpath目录下
//            ClassPathResource resource = new ClassPathResource(filename);

            //springboot 通过文件获取输入流不行
            inputStream =  new FileInputStream(filename);
          //  inputStream=resource.getInputStream();
        } catch (IOException e) {
            return null;
        }
        Properties properties = new Properties();
        try {
            properties.load(inputStream);
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                inputStream.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return  properties;
    }



    /**
     * 写入properties
     * @param filename
     */
    public  void writeToProperties(String filename,Map<String,String> map){
        Properties properties= loadProperties(filename);
        FileOutputStream fos=null;
        try {
      //      ClassPathResource resource = new ClassPathResource(filename);
           // String filePath= resource.getClassLoader().getResource("device.properties").getPath();
        //    File file= resource.getFile();
            fos=new FileOutputStream(filename);

     //        fos = new FileOutputStream(filePath,false); //默认是false ，表示获取流文件会重新覆盖之前的，true表示追加。
            //读取原来配置文件的数据加载到内存
            OutputStreamWriter opw = new OutputStreamWriter(fos,"utf-8");
            //新增的数据
            for(Map.Entry<String,String>entry:map.entrySet()){
//                if(properties.getProperty(entry.getKey())!=null){
//                    continue;
//                }
                properties.setProperty(entry.getKey(),entry.getValue());
            }


            properties.store(opw,"write data");
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                fos.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    /**
     * 清空
     * @param filename
     */
    public void clearProperties(String filename){
        Properties properties= loadProperties(filename);
        FileOutputStream fos=null;
        try {
   //         ClassPathResource resource = new ClassPathResource(filename);
//            String filePath= resource.getClassLoader().getResource("device.properties").getPath();
//            fos = new FileOutputStream(filePath,false);
      //      File file= resource.getFile();
            fos=new FileOutputStream(filename);
            OutputStreamWriter opw = new OutputStreamWriter(fos,"utf-8");
            properties.clear();
            properties.store(opw,"write data");
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                fos.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    /**
     * 将字节数组转为16进制字符串
     * @param bytes
     * @return
     */
    public static String Bytes2Hexstring(byte[]bytes){
        StringBuilder stringBuilder = new StringBuilder("");

        if (bytes == null || bytes.length <= 0) {
            return null;
        }
        for (int i = 0; i < bytes.length; i++) {
            int val = bytes[i] & 0xFF;  //截取为1个字节
            String str = Integer.toHexString(val);
            if (str.length() < 2) {
                stringBuilder.append(0); //小于半个字节，高位填补为0
            }
            stringBuilder.append(str);
        }

        return stringBuilder.toString().toUpperCase();
    }

    /**
     * 将16进制字符串转为字节数组
     * @param hexstr
     * @return
     */
    public static byte[] Hexstring2Bytes(String hexstr) {
        //两个十六进制字符串 对应一个字节，
        if(hexstr.length()%2!=0){
            hexstr=0+hexstr;
        }
        byte[] b = new byte[hexstr.length() / 2];
        int j = 0;
        for (int i = 0; i < b.length; i++) {
            char c0 = hexstr.charAt(j++);
            char c1 = hexstr.charAt(j++);
            b[i] = (byte) ((parse(c0) << 4) | parse(c1));
        }
        return b;
    }
    private static int parse(char c) {
        if (c >= 'a')
            return (c - 'a' + 10) & 0x0f;
        if (c >= 'A')
            return (c - 'A' + 10) & 0x0f;
        return (c - '0') & 0x0f;
    }

    /**
     * 16进制字符串转为10进制字符串
     * @param hexstring
     * @return
     */
    public static String Hexstring2Decimalstring(String hexstring){
        return Integer.parseInt(hexstring,16)+"";
    }

    /**
     * 将字节数组转为时间戳
     * @param bytes
     * @return
     */
    public static Long Bytes2Timestamp(byte[] bytes){
        if(bytes.length==10){
            //秒级时间戳
        }
        if(bytes.length==13){
            //毫秒级时间戳
        }
        return null;
    }

    public static String PercentNums(double nums){
        java.text.NumberFormat nf=java.text.NumberFormat.getPercentInstance();
        nf.setMinimumFractionDigits(2);
       return  nf.format(nums);
    }

    /**
     * 保留小数位数
     * @param num  待修正数字
     * @param size  小数位数
     * @return
     */
    public static double remainDecimal(double num,int size){
        BigDecimal b = new BigDecimal(num);
        Double result= b.setScale(size, BigDecimal.ROUND_HALF_UP).doubleValue();
        return result;
    }
}
