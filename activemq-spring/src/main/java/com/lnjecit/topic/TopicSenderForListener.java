package com.lnjecit.topic;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import javax.jms.Destination;

/**
 * @author lnj
 * @description 发布/订阅模式 消息发送方（使用消息监听器接收消息）
 * @date 2019-01-04 11:02
 **/
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"classpath:spring-activemq-topic-consumer-with-listener.xml"})
public class TopicSenderForListener {

    @Autowired
    private JmsTemplate jmsTemplate;

    @Test
    public void send() {
        sendMqMessage(null, "spring activemq topic type message!");
    }

    /**
     * 说明:发送的时候如果这里没有显示的指定destination.将用spring xml中配置的destination
     *
     * @param destination 队列
     * @param message     消息
     */
    public void sendMqMessage(Destination destination, final String message) {
        if (destination == null) {
            destination = jmsTemplate.getDefaultDestination();
        }
        jmsTemplate.send(destination, session -> session.createTextMessage(message));
        System.out.println("Topic: spring send message...");
    }
}
