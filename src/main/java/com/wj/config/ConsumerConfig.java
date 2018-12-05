package com.wj.config;

import com.wj.consumer.processor.impl.TestProcessor;
import com.wj.listener.MessageListener;
import lombok.Data;
import org.apache.rocketmq.client.consumer.DefaultMQPushConsumer;
import org.apache.rocketmq.client.exception.MQClientException;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @Author wangJun
 * @Description //TODO
 * @Date ${date} ${time}
 **/

@Configuration
@ConfigurationProperties(prefix = "rocketmq.consumer")
@Data
public class ConsumerConfig {

    private String namesrvAddr;
    private int consumeThreadMin;
    private int consumeThreadMax;

    @Bean(name = "testConsumer")
    public DefaultMQPushConsumer testConsumer() throws MQClientException {
        DefaultMQPushConsumer consumer = new DefaultMQPushConsumer();
        consumer.setNamesrvAddr(namesrvAddr);
        consumer.setConsumeThreadMin(consumeThreadMin);
        consumer.setConsumeThreadMax(consumeThreadMax);
        consumer.setConsumerGroup("testConsumerGroup");
        //consumer.setMessageListener(new MessageListener(new TestProcessor()));
        consumer.registerMessageListener(new MessageListener(new TestProcessor()));
        consumer.subscribe("test", "tag");
        consumer.start();
        return consumer;
    }
}
