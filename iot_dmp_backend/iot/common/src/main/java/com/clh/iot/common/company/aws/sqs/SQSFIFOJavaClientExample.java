package com.clh.iot.common.company.aws.sqs;

import com.amazonaws.AmazonClientException;
import com.amazonaws.AmazonServiceException;
import com.amazonaws.auth.AWSCredentials;
import com.amazonaws.auth.AWSStaticCredentialsProvider;
import com.amazonaws.auth.BasicAWSCredentials;
import com.amazonaws.regions.Regions;
import com.amazonaws.services.sqs.AmazonSQS;
import com.amazonaws.services.sqs.AmazonSQSClientBuilder;
import com.amazonaws.services.sqs.model.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

public class SQSFIFOJavaClientExample {
    private static final String key="AKIAJZ4NJBQQ3Y2VIE5A";
    private static final String secret="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";
    public static void main(String[] args) {
        /*
         * Create a new instance of the builder with all defaults (credentials
         * and region) set automatically. For more information, see
         * Creating Service Clients in the AWS SDK for Java Developer Guide.
         */
//        final AmazonSQS sqs = AmazonSQSClientBuilder.defaultClient();

        AWSCredentials awsCredentials=new BasicAWSCredentials(key,secret);
        AmazonSQS sqs=AmazonSQSClientBuilder.standard().withRegion(Regions.EU_WEST_1).withCredentials(new AWSStaticCredentialsProvider(awsCredentials)).build();

        System.out.println("===========================================");
        System.out.println("Getting Started with Amazon SQS FIFO Queues");
        System.out.println("===========================================\n");

        try {

            // Create a FIFO queue.
            System.out.println("Creating a new Amazon SQS FIFO queue called " +
                    "MyFifoQueue.fifo.\n");
            final Map<String, String> attributes = new HashMap<>();

            // A FIFO queue must have the FifoQueue attribute set to true.
            attributes.put("FifoQueue", "true");

            /*
             * If the user doesn't provide a MessageDeduplicationId, generate a
             * MessageDeduplicationId based on the content.
             */
            attributes.put("ContentBasedDeduplication", "true");

            // The FIFO queue name must end with the .fifo suffix.
            final CreateQueueRequest createQueueRequest =
                    new CreateQueueRequest("MyFifoQueue.fifo")
                            .withAttributes(attributes);
            final String myQueueUrl = sqs.createQueue(createQueueRequest).getQueueUrl();

            // List all queues.
            System.out.println("Listing all queues in your account.\n");
            for (final String queueUrl : sqs.listQueues().getQueueUrls()) {
                System.out.println("  QueueUrl: " + queueUrl);
            }
            System.out.println();

            // Send a message.
            System.out.println("Sending a message to MyFifoQueue.fifo.\n");
            final SendMessageRequest sendMessageRequest =
                    new SendMessageRequest(myQueueUrl,
                            "This is my message text.");

            /*
             * When you send messages to a FIFO queue, you must provide a
             * non-empty MessageGroupId.
             */
            sendMessageRequest.setMessageGroupId("messageGroup1");

            // Uncomment the following to provide the MessageDeduplicationId
            //sendMessageRequest.setMessageDeduplicationId("1");
            final SendMessageResult sendMessageResult = sqs
                    .sendMessage(sendMessageRequest);
            final String sequenceNumber = sendMessageResult.getSequenceNumber();
            final String messageId = sendMessageResult.getMessageId();
            System.out.println("SendMessage succeed with messageId "
                    + messageId + ", sequence number " + sequenceNumber + "\n");

            // Receive messages.
            System.out.println("Receiving messages from MyFifoQueue.fifo.\n");
            final ReceiveMessageRequest receiveMessageRequest =
                    new ReceiveMessageRequest(myQueueUrl);

            // Uncomment the following to provide the ReceiveRequestDeduplicationId
            //receiveMessageRequest.setReceiveRequestAttemptId("1");
            final List<Message> messages = sqs.receiveMessage(receiveMessageRequest)
                    .getMessages();
            for (final Message message : messages) {
                System.out.println("Message");
                System.out.println("  MessageId:     "
                        + message.getMessageId());
                System.out.println("  ReceiptHandle: "
                        + message.getReceiptHandle());
                System.out.println("  MD5OfBody:     "
                        + message.getMD5OfBody());
                System.out.println("  Body:          "
                        + message.getBody());
                for (final Entry<String, String> entry : message.getAttributes()
                        .entrySet()) {
                    System.out.println("Attribute");
                    System.out.println("  Name:  " + entry.getKey());
                    System.out.println("  Value: " + entry.getValue());
                }
            }
            System.out.println();

            // Delete the messages.
            System.out.println("Deleting the messages that we received.\n");

            for (final Message message : messages) {
                sqs.deleteMessage(new DeleteMessageRequest(myQueueUrl,
                        message.getReceiptHandle()));
            }

            // Delete the queue.
            System.out.println("Deleting the queue.\n");
            sqs.deleteQueue(new DeleteQueueRequest(myQueueUrl));
        } catch (final AmazonServiceException ase) {
            System.out.println("Caught an AmazonServiceException, which means " +
                    "your request made it to Amazon SQS, but was " +
                    "rejected with an error response for some reason.");
            System.out.println("Error Message:    " + ase.getMessage());
            System.out.println("HTTP Status Code: " + ase.getStatusCode());
            System.out.println("AWS Error Code:   " + ase.getErrorCode());
            System.out.println("Error Type:       " + ase.getErrorType());
            System.out.println("Request ID:       " + ase.getRequestId());
        } catch (final AmazonClientException ace) {
            System.out.println("Caught an AmazonClientException, which means " +
                    "the client encountered a serious internal problem while " +
                    "trying to communicate with Amazon SQS, such as not " +
                    "being able to access the network.");
            System.out.println("Error Message: " + ace.getMessage());
        }
    }
}
