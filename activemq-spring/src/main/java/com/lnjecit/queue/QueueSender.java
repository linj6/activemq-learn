package com.lnjecit.queue;


import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import javax.jms.Destination;

/**
 * @author lnj
 * @description 点对点模式 消息发送方
 * @date 2019-01-04 10:16
 **/
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"classpath:spring-activemq-queue.xml"})
public class QueueSender {

    @Autowired
    private JmsTemplate jmsTemplate;

    @Test
    public void send() {
        sendMqMessage(null, "spring activemq queue type message!");
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
        System.out.println("Queue: spring send message...");
    }
}
