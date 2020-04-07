package com.clh.iot.config;

import com.clh.iot.util.ClhUtils;

import javax.sound.midi.Soundbank;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;

public class Const {
    public static String DEVICE_PATH="device.properties";
 // public static String DEVICE_PATH="D:\\caolihua\\projects\\iot_platform\\iot_dmp_backend\\iot\\src\\main\\resources\\device.properties";
   public static int UDP_PACKAGE_NUMS=0;
    public static void main(String[] args) {
//        Properties properties = ClhUtils.loadProperties(Const.DEVICE_PATH);
//        int udpPackNums=  properties.size();
//        Map delayTimeMap = new HashMap();
//        Long delayTimeAll=0l;
//        for (String key : properties.stringPropertyNames()) {
//            String val= properties.getProperty(key);
//            String times[]=val.split(",");
//            Long time1=Long.valueOf(times[0]);
//            Long time2=Long.valueOf(times[1]);
//            Long delayTimeOne=time2-time1;
//            //delayTimeMap.put(key,delayTimeOne);
//            delayTimeAll=delayTimeAll+delayTimeOne;
//        }
//        //System.out.println(delayTimeMap);
//        System.out.println(delayTimeAll);
//        System.out.println(delayTimeAll/udpPackNums);
    }
}
