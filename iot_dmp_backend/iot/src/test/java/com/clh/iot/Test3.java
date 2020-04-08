package com.clh.iot;

import com.clh.iot.config.Const;
import com.clh.iot.util.ClhUtils;

import java.util.Properties;

public class Test3 {
    public static void main(String[] args) {
        ClhUtils clhUtils = new ClhUtils();
        Properties properties= clhUtils.loadProperties(Const.DEVICE_PATH);
        Object o= properties.get("0");
        System.out.println(o);
    }
}


