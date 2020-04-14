package com.clh.iot.networkService.netty.data;

import com.clh.iot.networkService.config.Const;

import java.io.UnsupportedEncodingException;

public class GenerateData {

    public static void main(String[] args) {
        byte bytes[]=GenerateData.prepareTcpBytes("A077777");
        System.out.println(bytes);
    }

    /**
     * 准备playload数据
     * @param lenght   字符串长度  ACSII 码
     * @return
     */
    public static String prepareUdpPlayLoad(int lenght){
        StringBuilder   sb = new StringBuilder();
        for(int i=0;i<lenght;i++){
            sb.append("A");
        }

        return sb.toString();
    }

    /**
     * 准备TCP数据
     * @return
     */
    public static byte[] prepareTcpBytes(String deviceId){
        int fileSize = Const.TCP_PACK_SIZE;  //1M
        int bytesSize= fileSize*1024*1024;
//        System.out.println(bytesSize);
        byte []bytes = new byte[bytesSize];
        bytes[0]=33;  // ! 开始符
        //设备ID  7 位
        byte deviceBytes[]=null;
        try {
             deviceBytes= deviceId.getBytes("ascii");
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        for(int i=0;i<deviceBytes.length;i++){
            bytes[i+1]=deviceBytes[i];
        }

        bytes[8]=38;
        for(int i=9;i<=bytesSize-1;i++){

            if(i%1024==0){
                bytes[i]=38;  // & 分隔符
                continue;
            }

            //结束符
            if(i==1048574){
                bytes[i]=35;   //# 结束符
                continue;
            }
            if(i==1048575){
                bytes[i]=38;   //& 分隔符
                continue;
            }
            bytes[i]=97;  //数据a
        }
        return bytes;
    }
}
