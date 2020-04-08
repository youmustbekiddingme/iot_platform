package com.clh.iot.config;

import com.clh.iot.util.ClhUtils;

import javax.sound.midi.Soundbank;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;

public class Const {
    public static String DEVICE_PATH="device.properties"; //设备文件time
    public static String UDP_SERVER_IP="192.168.0.59";//UDP 服务端地址
    public static String TCP_SERVER_IP="192.168.0.59";//TCP服务端地址
    public static int UDP_SERVER_PORT=10000;//UDP 端口号
    public static int TCP_SERVER_PORT=8080;//TCP端口号
   public static int UDP_PACKAGE_NUMS=0;  //UDP报文发送数
   public static int TCP_CHANNEL_KEEP_TIME=5000;  //TCP链路保持时间
    public static void main(String[] args) {

    }
}
