package com.clh.iot.common.company.aws.sqs;

import com.amazonaws.services.sqs.AmazonSQS;
import com.amazonaws.services.sqs.model.*;
import com.clh.iot.common.constP.SQSConst;

import java.util.Date;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;


public class SQSMultiThreadTest   {
    public static void main(String[] args) {
        SQSMultiThreadTest sqsMultiThreadTest = new SQSMultiThreadTest();
        sqsMultiThreadTest.start();;
    }


public void start(){
    ConsumerTask consumerTask = new ConsumerTask();
    ExecutorService fixedThreadPool = Executors.newFixedThreadPool(30);
    for (int i = 0; i < SQSConst.THREAD_NUMS; i++) {
        fixedThreadPool.execute(consumerTask);
    }

}

    /**
     * 消费者类
     */
    public class ConsumerTask implements  Runnable{

        @Override
        public void run() {
            while(true){
                AmazonSQS sqs= SQSConst.getSQSQueue();
                CreateQueueRequest createQueueRequest =
                        new CreateQueueRequest("MyQueue");
                String myQueueUrl = sqs.createQueue(createQueueRequest).getQueueUrl();
                System.out.println(myQueueUrl);
                ReceiveMessageRequest request = new ReceiveMessageRequest().withQueueUrl(myQueueUrl).withMaxNumberOfMessages(10).withWaitTimeSeconds(SQSConst.LONG_POLLING_PERIOD);
                System.out.println(SQSConst.LONG_POLLING_PERIOD);
                List<Message> messageList=  sqs.receiveMessage(request).getMessages();
                System.out.println("messageSize:" +messageList.size());
                for(Message message:messageList){
                    System.out.println(message.getBody());
                    sqs.deleteMessage(myQueueUrl,message.getReceiptHandle());
                    System.out.println(new Date());
                }
            }

        }
    }

    public void produceMessage(){
        AmazonSQS sqs= SQSConst.getSQSQueue();
        CreateQueueRequest createQueueRequest =
                new CreateQueueRequest("MyQueue");
        String myQueueUrl = sqs.createQueue(createQueueRequest).getQueueUrl();
        for(int i=0;i<1000;i++){
            final SendMessageRequest sendMessageRequest =
                    new SendMessageRequest(myQueueUrl,
                            "This is my message text."+i);

             SendMessageResult sendMessageResult = sqs
                    .sendMessage(sendMessageRequest);
        }

    }
}
