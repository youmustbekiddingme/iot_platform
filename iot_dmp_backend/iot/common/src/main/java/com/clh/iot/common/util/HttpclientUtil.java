package com.clh.iot.common.util;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.message.BasicNameValuePair;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
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

    public static void main(String[] args) {
        List<NameValuePair> params=new ArrayList<NameValuePair>();
        NameValuePair param1 = new BasicNameValuePair("camera_id","A111111");
        params.add(param1);
        executeWithUrlencoded("https://ss.wiwacam.com:8443/Echo/doorbellRing",params);
    }


    /**
     * Urlencoded编码方式执行post请求
     * @param url
     * @param params
     * @return
     */
    public static Map<String,String> executeWithUrlencoded(String url, List<NameValuePair> params){
        String result="";
        String statusCode="";

        CloseableHttpClient httpClient = HttpClients.createDefault();
        HttpPost httpPost = new HttpPost(url);
        httpPost.addHeader("Content-Type","application/x-www-form-urlencoded");
        try {
        httpPost.setEntity(new UrlEncodedFormEntity(params,"UTF-8"));

        HttpResponse response =httpClient.execute(httpPost);
            statusCode=response.getStatusLine().getStatusCode()+"";
            HttpEntity entity=response.getEntity();
            if(entity!=null){
                InputStream inputStream = entity.getContent();
                result=convertStreamToString(inputStream);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        Map mapRes = new HashMap();
        mapRes.put("statusCode",statusCode);
        mapRes.put("result",result);
        return mapRes;
    }

    /**
     * Json 编码方式执行post请求
     * @param url
     * @param json
     * @return
     */
    public static Map<String,String> executeWithJson(String url,String json){
        String result="";
        String statusCode="";

        CloseableHttpClient httpClient = HttpClients.createDefault();
        HttpPost httpPost = new HttpPost(url);
        httpPost.addHeader("Content-Type","application/json");
        httpPost.setEntity(new StringEntity(json,"UTF-8"));

        try {
            HttpResponse response =httpClient.execute(httpPost);
            statusCode=response.getStatusLine().getStatusCode()+"";
            HttpEntity entity=response.getEntity();
            if(entity!=null){
                InputStream inputStream = entity.getContent();
                result=convertStreamToString(inputStream);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        Map mapRes = new HashMap();
        mapRes.put("statusCode",statusCode);
        mapRes.put("result",result);
        return mapRes;
    }

    private static String convertStreamToString(InputStream is){
        BufferedReader reader = new BufferedReader(new InputStreamReader(is));
        StringBuilder sb = new StringBuilder();
        String line=null;
        try {
            while ((line=reader.readLine())!=null){
                sb.append(line+"\n");
            }
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                is.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return sb.toString();
    }
}
