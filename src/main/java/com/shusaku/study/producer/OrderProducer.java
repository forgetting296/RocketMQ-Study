package com.shusaku.study.producer;

import org.apache.rocketmq.client.exception.MQBrokerException;
import org.apache.rocketmq.client.exception.MQClientException;
import org.apache.rocketmq.client.producer.DefaultMQProducer;
import org.apache.rocketmq.client.producer.MessageQueueSelector;
import org.apache.rocketmq.client.producer.SendResult;
import org.apache.rocketmq.common.message.Message;
import org.apache.rocketmq.common.message.MessageQueue;
import org.apache.rocketmq.remoting.common.RemotingHelper;
import org.apache.rocketmq.remoting.exception.RemotingException;

import java.io.UnsupportedEncodingException;
import java.util.List;

/**
 * @program: rocket-mq
 * @description:
 * @author: Shusaku
 * @create: 2020-03-30 15:12
 */
public class OrderProducer {

    public static void main(String[] args) throws MQClientException, UnsupportedEncodingException, RemotingException, InterruptedException, MQBrokerException {

        //启动broker的时候　　需要加-n ip:port   如./mqbroker -n 127.0.0.1:9876  否则报错　　MQClientException: No route info for this topic, orderTopic　
        /*DefaultMQProducer producer = new DefaultMQProducer("order_group");
        producer.setNamesrvAddr("127.0.0.1:9876");
        producer.start();
        Message message;
        for(int i = 0;i < 10;i ++) {
            int orderedId = i;
            message = new Message("orderTopic","test", "KEY" + i, ("Hello Ordered Message").getBytes(RemotingHelper.DEFAULT_CHARSET));
            SendResult result = producer.send(message, new MessageQueueSelector() {
                @Override
                public MessageQueue select(List<MessageQueue> mqs, Message msg, Object arg) {
                    System.out.println("queue selector mq nums: " + mqs.size());
                    System.out.println("message info: " + msg.toString());
                    for (MessageQueue queue : mqs) {
                        System.out.println(queue.toString());
                    }
                    Integer id = (Integer) arg;
                    int index = id % mqs.size();
                    return mqs.get(index);
                }
            }, orderedId);
            System.out.println("result: " + result);
        }*/

        DefaultMQProducer producer = new DefaultMQProducer("test_group");
        producer.setNamesrvAddr("127.0.0.1:9876");
        //producer.setRetryTimesWhenSendAsyncFailed(3);
        producer.start();
        Message message;
        for(int i = 0;i < 10;i ++) {
            message = new Message("testTopic", ("Hello Ordered Message" + i).getBytes(RemotingHelper.DEFAULT_CHARSET));
            SendResult sendResult = producer.send(message);
            System.out.println("result: " + sendResult);
        }
        //producer.shutdown();
    }

}
