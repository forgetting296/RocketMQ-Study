package com.shusaku.study.filter;

import org.apache.rocketmq.common.filter.FilterContext;
import org.apache.rocketmq.common.filter.MessageFilter;
import org.apache.rocketmq.common.message.MessageExt;

/**
 * @program: rocket-mq
 * @description: 自定义Filter Server  根据SequenceId这个属性进行过滤　id 大于10　并且　能被3整除
 * @author: Shusaku
 * @create: 2020-03-30 19:06
 */
public class MessageFilterImpl implements MessageFilter {
    @Override
    public boolean match(MessageExt msg, FilterContext context) {

        String sequenceId = msg.getUserProperty("SequenceId");
        if(sequenceId != null) {
            int id = Integer.parseInt(sequenceId);
            if((id % 3) == 0 && (id > 10)) {
                return true;
            }
        }

        return false;
    }
}
