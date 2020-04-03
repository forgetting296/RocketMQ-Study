package com.shusaku.study.consumer;

import org.apache.rocketmq.client.consumer.DefaultMQPushConsumer;
import org.apache.rocketmq.client.consumer.listener.ConsumeConcurrentlyContext;
import org.apache.rocketmq.client.consumer.listener.ConsumeConcurrentlyStatus;
import org.apache.rocketmq.client.consumer.listener.MessageListenerConcurrently;
import org.apache.rocketmq.client.exception.MQClientException;
import org.apache.rocketmq.common.MixAll;
import org.apache.rocketmq.common.message.MessageExt;
import org.apache.rocketmq.common.protocol.heartbeat.MessageModel;

import java.io.IOException;
import java.util.List;

/**
 * @program: rocket-mq
 * @description:
 * @author: Shusaku
 * @create: 2020-03-30 19:14
 */
public class FilterByCodeConsumer {

    public static void main(String[] args) throws IOException, MQClientException {

        DefaultMQPushConsumer consumer = new DefaultMQPushConsumer("test_filter_group");
        consumer.setNamesrvAddr("127.0.0.1:9876");
        //consumer.setConsumeFromWhere(ConsumeFromWhere.CONSUME_FROM_FIRST_OFFSET);
        consumer.setMessageModel(MessageModel.BROADCASTING);
        String filterCode = MixAll.file2String("/liuzp/workspace/rocket-mq/src/main/java/com/shusaku/study/filter/MessageFilterImpl.java");
        consumer.subscribe("TopicFilter", "com.shusaku.study.consumer/MessageFilterImpl", filterCode);
        consumer.registerMessageListener(new MessageListenerConcurrently() {
            @Override
            public ConsumeConcurrentlyStatus consumeMessage(List<MessageExt> msgs, ConsumeConcurrentlyContext context) {

                return ConsumeConcurrentlyStatus.CONSUME_SUCCESS;
            }
        });

    }

}
