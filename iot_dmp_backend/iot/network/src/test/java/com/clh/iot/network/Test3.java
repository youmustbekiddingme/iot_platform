package com.clh.iot.network;

import java.io.RandomAccessFile;

public class Test3 {
    public static void main(String[] args) throws Exception {
        RandomAccessFile file = new RandomAccessFile("d:\\test.txt", "rw");
        file.write( 33 );// ！表示开始
        file.write( 38 );  //&表示分割
        for(int i=3;i<=1048576;i++){

            //&分隔符
            if(i%1024==0){
                file.write(38);
                continue;
            }

            //结束符
            if(i==1048575){
                file.write(35);
                continue;
            }
            //普通数据a
            file.write( 97 );
        }





//        file.write( 33 );// ！表示开始
//        file.write( 38 );  //&表示分割
//
//        for(int i=1;i<=20;i++){
//            if(i%4==0){
//                file.write(38);
//                continue;
//            }
//            file.write( 97 );  //普通数据a
//        }
//
//        file.write( 35 );// #表示结束
//        file.write( 38 );  //&表示分割

//        f.setLength(1024 * 1024);  //设置其大小为1M。
//        InputStream in=new FileInputStream("d:\\test.txt");
//        //1.分配一块内存空间 临时的空间 存放我文件的数据
//        byte[] b=new byte[in.available()];
//        //2.将数据读入到内存空间
//        in.read(b);

    }
}


