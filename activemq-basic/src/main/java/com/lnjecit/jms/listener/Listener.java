package com.lnjecit.jms.listener;

import javax.jms.JMSException;
import javax.jms.Message;
import javax.jms.MessageListener;
import javax.jms.TextMessage;

/**
 * @author lnj
 * @description 消息监听器
 * @date 2019-01-03 21:21
 **/
public class Listener implements MessageListener {
    @Override
    public void onMessage(Message message) {
        if (message instanceof TextMessage) {
            try {
                System.out.println(((TextMessage) message).getText());
            } catch (JMSException e) {
                e.printStackTrace();
            }
        } else {
            System.out.println("Unexpected message type: " + message.getClass());
        }
    }
}
