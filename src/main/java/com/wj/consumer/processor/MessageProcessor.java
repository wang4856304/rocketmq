package com.wj.consumer.processor;

import org.apache.rocketmq.common.message.MessageExt;

/**
 * @Author wangJun
 * @Description //TODO
 * @Date ${date} ${time}
 **/
public interface MessageProcessor {

    /**
     * 处理消息的接口
     * @param messageExt
     * @return
     */
    boolean handleMessage(MessageExt messageExt);
}
