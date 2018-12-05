package com.wj;

import com.wj.consumer.processor.impl.TestProcessor;
import com.wj.listener.MessageListener;
import org.apache.rocketmq.client.consumer.DefaultMQPushConsumer;
import org.apache.rocketmq.client.consumer.MQConsumer;
import org.apache.rocketmq.client.producer.DefaultMQProducer;
import org.apache.rocketmq.client.producer.MQProducer;
import org.apache.rocketmq.common.message.Message;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;

/**
 * @Author wangJun
 * @Description //TODO
 * @Date ${date} ${time}
 **/

@SpringBootApplication
public class RocketMQApp implements ApplicationContextAware {

    private static ApplicationContext context;

    public static void main(String args[]) throws Exception {
        SpringApplication.run(RocketMQApp.class, args);
        MQProducer producer = context.getBean("testProducer", MQProducer.class);

        /*DefaultMQProducer producer = new DefaultMQProducer();
        producer.setNamesrvAddr("10.50.20.166:9876");
        producer.setMaxMessageSize(4096);
        producer.setSendMsgTimeout(10000);
        producer.setProducerGroup("testProducerGroup");
        producer.start();
        //producer.setCreateTopicKey("AUTO_CREATE_TOPIC_KEY");*/
        Message message = new Message();
        message.setTopic("test");
        message.setTags("tag");
        message.setKeys("key");
        message.setBody("hello world".getBytes());
        producer.send(message);

        /*DefaultMQPushConsumer consumer = new DefaultMQPushConsumer();
        consumer.setNamesrvAddr("10.50.20.166:9876");
        consumer.setConsumeThreadMin(20);
        consumer.setConsumeThreadMax(64);
        consumer.setConsumerGroup("testConsumerGroup");
        //consumer.setMessageListener(new MessageListener(new TestProcessor()));
        consumer.registerMessageListener(new MessageListener(new TestProcessor()));
        consumer.subscribe("test", "tag");
        consumer.start();*/

    }

    @Override
    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
        RocketMQApp.context = applicationContext;
    }
}
