package com.clh.iot.networkService.test;

import com.clh.iot.networkService.util.ClhUtils;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;

import java.util.Properties;

public class Test {
    public static void main(String[] args) throws Exception{
        Test test = new Test();
        ClhUtils clhUtils = new ClhUtils();
//        String path = Test.class.getResource("/").toString()+"device.properties";
        Resource resource = new ClassPathResource("device.properties");

        Properties properties = clhUtils.loadProperties(resource.getFile().getAbsolutePath());
        System.out.println(resource.getFile().getAbsolutePath());
    }
}
