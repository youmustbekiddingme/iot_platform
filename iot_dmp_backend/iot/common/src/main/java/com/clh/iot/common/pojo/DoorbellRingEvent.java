package com.clh.iot.common.pojo;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

public class DoorbellRingEvent {
	public static  Map  getDoorbellRingEvent(String token,String endpointId){
		Map map  = new HashMap();
		
		Map contextMap = new HashMap();
		map.put("context", contextMap);
		
		Map eventMap= new HashMap();
		map.put("event", eventMap);
		
		Map headerMap = new HashMap();
		Long messageId= System.currentTimeMillis();
		headerMap.put("messageId",messageId+"clh");
		headerMap.put("namespace","Alexa.DoorbellEventSource");
		headerMap.put("name","DoorbellPress");
		headerMap.put("payloadVersion","3");

		Map endpointMap=new HashMap();
		Map scopeMap = new HashMap();
		scopeMap.put("type", "BearerToken");
		scopeMap.put("token", token);
		endpointMap.put("scope", scopeMap);
		endpointMap.put("endpointId", endpointId);
		
		Map payloadMap = new HashMap();
		Map causeMap = new HashMap();
		causeMap.put("type", "PHYSICAL_INTERACTION");
		payloadMap.put("cause", causeMap);
		SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSS'Z'");
		Date date= new Date();
		String str=format.format(date);
		payloadMap.put("timestamp", ""+str);

		eventMap.put("header", headerMap);
		eventMap.put("endpoint", endpointMap);
		eventMap.put("payload", payloadMap);
		
		return map;
	}
}
