package com.shusaku.study.producer;

import org.apache.rocketmq.client.exception.MQBrokerException;
import org.apache.rocketmq.client.exception.MQClientException;
import org.apache.rocketmq.client.producer.DefaultMQProducer;
import org.apache.rocketmq.client.producer.SendCallback;
import org.apache.rocketmq.client.producer.SendResult;
import org.apache.rocketmq.common.message.Message;
import org.apache.rocketmq.remoting.exception.RemotingException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;

/**
 * @program: rocket-mq
 * @description:
 * @author: Shusaku
 * @create: 2020-03-30 10:43
 */
@Component
public class RocketMqProducer {

    private static final Logger logger = LoggerFactory.getLogger(RocketMqProducer.class);

    @Value("${rocketmq.producer.groupName}")
    private String groupName;
    @Value("${rocketmq.producer.namesrvAddr}")
    private String nameserAddr;
    @Value("${rocketmq.producer.instanceName}")
    private String instanceName;
    @Value("${rocketmq.producer.maxMessageSize}")
    private int maxMessageSize;
    @Value("${rocketmq.producer.sendMsgTimeout}")
    private int sendMsgTimeout;

    @Value("${rocketmq.consumer.topic}")
    private String topic;
    @Value("${rocketmq.consumer.tag}")
    private String tag;

    private DefaultMQProducer producer;

    @Bean
    public DefaultMQProducer getRocketMqProducer() {

        producer = new DefaultMQProducer();
        producer.setProducerGroup(groupName);
        producer.setNamesrvAddr(nameserAddr);
        producer.setMaxMessageSize(maxMessageSize);
        producer.setInstanceName(instanceName);
        producer.setSendMsgTimeout(sendMsgTimeout);
        producer.setVipChannelEnabled(false);
        producer.setCreateTopicKey("AUTO_CREATE_TOPIC_KEY");

        try {
            producer.start();

            Message message = null;
            for(int i = 0;i < 100;i ++) {
                message = new Message(topic,tag,("hello rocketmq" + i).getBytes());
                producer.send(message);
                //这个方法是异步的　　吞吐量高　响应快　　但是　可用性低　如果系统异常容易造成消息丢失
                /*producer.send(message, new SendCallback() {
                    @Override
                    public void onSuccess(SendResult sendResult) {
                        //发送成功之后的操作
                    }

                    @Override
                    public void onException(Throwable e) {
                        //发送失败之后对应的操作
                    }
                });*/
            }
        } catch (MQClientException | RemotingException | InterruptedException | MQBrokerException e) {
            e.printStackTrace();
        }

        return producer;
    }

}
