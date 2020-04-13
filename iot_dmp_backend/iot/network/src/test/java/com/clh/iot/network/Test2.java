package com.clh.iot.network;

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
