package com.clh.iot.networkService.config;


public class Const {
    public static String DEVICE_PATH="device.properties"; //设备文件time
    public static String TCP_SERVER_IP="192.168.0.59";//TCP服务端地址
    public static int TCP_SERVER_PORT=9999;//TCP端口号
   public static int TCP_CHANNEL_KEEP_TIME=5000;  //TCP链路保持时间
    public static int TCP_PACK_SIZE=1; // 1M

    public static int UDP_SERVER_DELAY_TIME=0;  // UDPserver模拟延迟时间
    public static int UDP_HEART_BEAT=200;//UDP心跳检测
    public static int UDP_SEND_REC_DELAY_TIME=3000; //UDP报文发送应答总时间
    public static int UDP_PACKAGE_NUMS=100;  //UDP报文发送数
    public static int UDP_SERVER_PORT=10000;//UDP端口号
    public static String UDP_SERVER_IP="192.168.0.59";//UDP服务端地址
    public static String deviceId="A077468";


}
