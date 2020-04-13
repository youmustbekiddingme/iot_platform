package com.clh.iot.networkService;


import java.util.ArrayList;
import java.util.List;

public class Test{

    //原子类
    private volatile List<String> list = new ArrayList<String>();
    private Object lock = new Object();

    //t2线程
    public void put(){
        synchronized (lock) {
            for(int i=0;i<1000;i++){

                list.add("A"+i);
                System.out.println("线程"+Thread.currentThread().getName()+"添加第"+i+"个元素");

                    //数据准备好了,发出唤醒通知,但是不释放锁
            }
            try {
                Thread.sleep(1000);
                lock.notify();
                System.out.println("发出通知...");
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    //t1线程
    public void get(){
        synchronized (lock) {
            try {
                System.out.println("线程"+Thread.currentThread().getName()+"业务处理,发现有需要的数据没准备好,则发起等待");
                System.out.println("线程"+Thread.currentThread().getName()+"wait");
                lock.wait(); //wait操作释放锁,否则其他线程无法进入put方法
                System.out.println("线程"+Thread.currentThread().getName()+"被唤醒");
                for(String s:list){
                    System.out.println("线程"+Thread.currentThread().getName()+"获取元素:"+s);
                }
            } catch (InterruptedException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
        }
    }

    public static void main(String[] args) {

        final Test demo = new Test();

        new Thread(new Runnable() {
            @Override
            public void run() {
                demo.get();
            }
        },"t1").start();;


        new Thread(new Runnable() {
            @Override
            public void run() {
                demo.put();
            }
        },"t2").start();

    }


}

