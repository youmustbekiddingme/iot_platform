package com.clh.iot.doorbellService.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.Map;

@RequestMapping("/doorbell")
@Controller
public class DoorbellController {


    /**
     * json 编码方式请求
     * @param param
     * @return
     */
    @RequestMapping("/deviceList1")
    @ResponseBody
    public Object devicelist1(@RequestBody String param){
        System.out.println(param);
        Map mapRes = new HashMap();
        mapRes.put("xxx","yyyy");
        return  mapRes;
    }

    /**
     * urlencoded 编码方式请求
     * @param map
     * @return
     */
    @RequestMapping("/deviceList2")
    @ResponseBody
    public Object devicelist2(@RequestParam Map  map){
        String content = (String)map.get("content");
        System.out.println(content);
        Map mapRes = new HashMap();
        mapRes.put("xxx","yyyy");
        return  mapRes;
    }
}
