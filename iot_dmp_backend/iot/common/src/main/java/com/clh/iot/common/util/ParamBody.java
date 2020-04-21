package com.clh.iot.common.util;

import com.google.gson.Gson;

import java.util.Map;

public class ParamBody {
    /**
     * 前端传过来的数据
     * @param body
     * @return
     */
    public static Map<String,String> getBodyMap(String body){
        Gson gson = new Gson();
        Map mapP =gson.fromJson(body,Map.class);
        Map<String,String> bodyMap =(Map) mapP.get("body");
        return bodyMap;
    }

    public static void main(String[] args) {

    }
}
