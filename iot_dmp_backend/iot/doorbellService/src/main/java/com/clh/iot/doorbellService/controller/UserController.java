package com.clh.iot.doorbellService.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.net.URLDecoder;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

@RequestMapping("/user")
@Controller
public class UserController {
    Map map = new HashMap();

    @RequestMapping("/token")
    @ResponseBody
    public Object getToken(HttpServletRequest request){
      String code=   request.getParameter("code");
        String token = new Date().getTime()+"";
        map.put(code,token);
        Map res= new HashMap();
        res.put("access_token",token);
        res.put("token_type","bearer");
        return res;
    }

    @RequestMapping("/login")
    public void login(HttpServletRequest request , HttpServletResponse response){
        String redirect_uri = request.getParameter("redirect_uri");
        String state=request.getParameter("state");
        String code = "clh"+System.currentTimeMillis();
        String url = URLDecoder.decode(redirect_uri)
                +"?state="+state+"&code="+code;
       // String url2="www.baidu.com";
        try {
            response.sendRedirect(url);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
