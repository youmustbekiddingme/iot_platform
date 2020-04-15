package com.clh.iot.networkService.test;

import com.clh.iot.networkService.config.Const;
import com.clh.iot.networkService.util.ClhUtils;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;

import java.io.File;
import java.io.InputStream;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;

public class Test {
    public static void main(String[] args) throws Exception{

        ClassPathResource resource = new ClassPathResource("device.properties");

        File file=  resource.getFile();

    }
}
