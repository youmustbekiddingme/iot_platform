package com.clh.iot.common.controller;

import com.amazonaws.services.sqs.AmazonSQS;
import com.amazonaws.services.sqs.model.CreateQueueRequest;
import com.amazonaws.services.sqs.model.SendMessageRequest;
import com.amazonaws.services.sqs.model.SendMessageResult;
import com.clh.iot.common.company.aws.sqs.SQSMultiThreadTest;
import com.clh.iot.common.constP.SQSConst;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/message")
public class MessageController {

    @RequestMapping("/produce")
    public Object produceMessage(){


        Map map = new HashMap();
        AmazonSQS sqs= SQSConst.getSQSQueue();
        CreateQueueRequest createQueueRequest =
                new CreateQueueRequest("MyQueue");
        String myQueueUrl = sqs.createQueue(createQueueRequest).getQueueUrl();

        Thread thread = new Thread(new Runnable() {
            @Override
            public void run() {
                for(int i=0;i<1000;i++){
                    final SendMessageRequest sendMessageRequest =
                            new SendMessageRequest(myQueueUrl,
                                    "text message:"+i);
                    SendMessageResult sendMessageResult = sqs
                            .sendMessage(sendMessageRequest);

                }
            }
        });
        thread.start();
        map.put("result","success");
        return map;
    }

    @RequestMapping("/consume")
    public Object messageConsume(){
        Thread thread = new Thread(new Runnable() {
            @Override
            public void run() {
                SQSMultiThreadTest sqsMultiThreadTest = new SQSMultiThreadTest();
                sqsMultiThreadTest.start();
            }
        });
        thread.start();;
        Map map = new HashMap();
        map.put("consume","secuess");
        return map;
    }
    @RequestMapping("/setTime")
    public Object setLongPollingTime(int time){
        SQSConst.LONG_POLLING_PERIOD=time;
        System.out.println(SQSConst.LONG_POLLING_PERIOD);
        Map map = new HashMap();
        map.put("time",time);
        return map;
    }
}
