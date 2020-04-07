package com.clh.iot.task;

import java.util.concurrent.TimeUnit;

public class ThreadTaskId implements  Runnable{
    private final int id;

    public ThreadTaskId(int id) {
        this.id = id;
    }

    public void run() {

            System.out.println("TaskInPool-["+id+"] is running phase-");

    }
}
