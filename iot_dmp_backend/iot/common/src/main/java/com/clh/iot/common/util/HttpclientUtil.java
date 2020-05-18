package com.clh.iot.common.util;

import org.apache.http.NameValuePair;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 网上说的是需要引入3个包
 * 依赖引入包
 * 1.httpcore4.4.10  这里pom没有引入，但是不知道在哪里加进来了
 * eclispe项目非maven 引入需要
 * 2.
 * <dependency>
 <groupId>org.apache.httpcomponents</groupId>
 <artifactId>httpclient</artifactId>
 <version>4.5.12</version>
 </dependency>
 3.还需要 logging   <artifactId>commons-logging</artifactId>

 ******************************这里只是引入了httpclient
 */
public class HttpclientUtil {

    /**
     * Urlencoded编码方式执行post请求
     * @param url
     * @param params
     * @return
     */
    public static Map<String,String> executeWithUrlencoded(String url, List<NameValuePair> params){
        Map mapRes = new HashMap();
        return mapRes;
    }

    /**
     * Json 编码方式执行post请求
     * @param url
     * @param json
     * @return
     */
    public static Map<String,String> executeWithJson(String url,String json){
        Map mapRes = new HashMap();
        return mapRes;
    }
}
