package com.lnjecit.topic;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import javax.jms.JMSException;
import javax.jms.Message;
import javax.jms.TextMessage;

/**
 * @author lnj
 * @description 发布/订阅模式 消息接收方
 * @date 2019-01-04 10:24
 **/
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"classpath:spring-activemq-topic.xml"})
public class TopicReceiver {

    @Autowired
    private JmsTemplate jmsTemplate;

    @Test
    public void receive() throws JMSException {
        while (true) {
            Message message = jmsTemplate.receive(jmsTemplate.getDefaultDestination());
            if (message instanceof TextMessage) {
                System.out.println(((TextMessage) message).getText());
            }
        }
    }
}
