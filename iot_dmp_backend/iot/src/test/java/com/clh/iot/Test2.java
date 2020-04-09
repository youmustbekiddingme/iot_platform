package com.clh.iot;

import com.clh.iot.config.Const;
import com.clh.iot.util.ClhUtils;

import java.util.Scanner;

public class Test2 {
    public static void main(String[] args) {
        int length=1000;
        StringBuilder stringBuilder = new StringBuilder(    );
        for(int i=0;i<length;i++){
            stringBuilder.append("A");
        }
        String playLoad= stringBuilder.toString();
        System.out.println(playLoad);
    }
}
