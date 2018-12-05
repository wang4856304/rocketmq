package com.wj.config;

import lombok.Data;
import org.apache.rocketmq.client.exception.MQClientException;
import org.apache.rocketmq.client.producer.DefaultMQProducer;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @Author wangJun
 * @Description //TODO
 * @Date ${date} ${time}
 **/

@Configuration
@ConfigurationProperties(prefix = "rocketmq.producer")
@Data
public class ProducerConfig {

    private String groupName;
    private String namesrvAddr;
    private int maxMessageSize;
    private int sendMsgTimeout;

    @Bean("testProducer")
    public DefaultMQProducer testProducer() throws MQClientException {
        DefaultMQProducer producer = new DefaultMQProducer();
        producer.setNamesrvAddr(namesrvAddr);
        producer.setMaxMessageSize(maxMessageSize);
        producer.setSendMsgTimeout(sendMsgTimeout);
        producer.setProducerGroup("testProducerGroup");
        producer.start();
        //producer.setCreateTopicKey("AUTO_CREATE_TOPIC_KEY");//这行存在会报错？？？？
        return producer;
    }
}
