package com.wj.listener;

import com.wj.consumer.processor.MessageProcessor;
import org.apache.rocketmq.client.consumer.listener.ConsumeConcurrentlyContext;
import org.apache.rocketmq.client.consumer.listener.ConsumeConcurrentlyStatus;
import org.apache.rocketmq.client.consumer.listener.MessageListenerConcurrently;
import org.apache.rocketmq.common.message.MessageExt;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.List;

/**
 * @Author wangJun
 * @Description //TODO
 * @Date ${date} ${time}
 **/
public class MessageListener implements MessageListenerConcurrently {

    private static Logger logger = LoggerFactory.getLogger(MessageListener.class);

    private MessageProcessor messageProcessor;

    public MessageListener(MessageProcessor messageProcessor) {
        this.messageProcessor = messageProcessor;
    }

    @Override
    public ConsumeConcurrentlyStatus consumeMessage(List<MessageExt> list, ConsumeConcurrentlyContext consumeConcurrentlyContext) {

        for (MessageExt m: list) {
            boolean result = messageProcessor.handleMessage(m);
            if (!result) {
                logger.warn("error handle message, it will handle later, message={}", m.toString());
            }
        }
        return ConsumeConcurrentlyStatus.CONSUME_SUCCESS;
    }
}
