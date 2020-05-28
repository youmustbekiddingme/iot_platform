package com.clh.iot.common.company.ali.oss;

import com.aliyun.mns.client.CloudQueue;
import com.aliyun.mns.model.Message;
import com.google.gson.Gson;

import java.util.List;
import java.util.Map;

public class MNSMessageHandler {

    private static final String EVENT_NAME_PUT="ObjectCreated:PutObject";
    public static void main(String[] args) {
        MNSMessageHandler mnsMessageHandler = new MNSMessageHandler();
        mnsMessageHandler.start();;
    }

    public void start(){
        HandlerTask handlerTask=new HandlerTask();
        Thread thread = new Thread(handlerTask);
        thread.start();
    }

    private class HandlerTask implements Runnable{
        @Override
        public void run() {
            CloudQueue cloudQueue = OssUtil.client.getQueueRef("MyQueue");
            Gson gson = new Gson();
            while(true){
                List<Message> messageList = cloudQueue.batchPopMessage(10,5);
                //阿里云消息不存在，拉取为null；亚马逊 拉取不存在不为null， 是size=0;
             //   System.out.println(messageList);
                if(messageList!=null){

                    for(Message message:messageList){
                      String messageBody=  message.getMessageBody();
                      Map messageMap = gson.fromJson(messageBody,Map.class);
                      List eventList=(List)messageMap.get("events");
                        for(Object object: eventList){
                            Map map=(Map)object;

                            String eventName=(String)map.get("eventName");
                            if(eventName.equals(EVENT_NAME_PUT)){
                                String eventTime=(String)map.get("eventTime");
                                Map ossMap=(Map)map.get("oss");
                                Map bucketMap =(Map)ossMap.get("bucket");
                                String bucketName=(String)bucketMap.get("name");

                                Map objectMap = (Map)ossMap.get("object");
                                String key=(String)objectMap.get("key");
                                double size=(double)objectMap.get("size");

                                String region=(String)map.get("region");
                                System.out.println("eventName:"+eventName);
                                System.out.println("eventTime:"+eventTime);
                                System.out.println("bucketName:"+bucketName);
                                System.out.println("key:"+key);
                                System.out.println("size:"+size);
                                System.out.println("region:"+region);
                            }else{
                                System.out.println("other event");
                            }


                        }



                        System.out.println(messageBody);
                        System.out.println(messageMap);
                    }
                }
            }



        }
    }
}
