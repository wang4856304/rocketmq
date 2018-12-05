package com.wj.consumer.processor.impl;

import com.wj.consumer.processor.MessageProcessor;
import org.apache.rocketmq.common.message.MessageExt;

/**
 * @Author wangJun
 * @Description //TODO
 * @Date ${date} ${time}
 **/
public class TestProcessor implements MessageProcessor {
    @Override
    public boolean handleMessage(MessageExt messageExt) {
        System.out.println(new String(messageExt.getBody()));
        return true;
    }
}
