package com.lnjecit.jms.consumer;

import com.lnjecit.jms.constants.ActiveMQConstants;
import com.lnjecit.jms.util.ConnectionUtil;

import javax.jms.*;

/**
 * @author lnj
 * @description activemq 发布/订阅模式 消息接收方
 * @date 2019-01-03 14:43
 **/
public class JmsSubscriber {
    public static void main(String[] args) {
        // 获取连接
        Connection connection = null;

        Session session = null;
        try {
            connection = ConnectionUtil.getConnection();

            // 启动连接
            connection.start();

            // 开启事务性会话
            session = connection.createSession(true, Session.AUTO_ACKNOWLEDGE);

            // 创建队列
            Destination destination = session.createTopic(ActiveMQConstants.NEWS_TOPIC);

            // 创建消费者
            MessageConsumer consumer = session.createConsumer(destination);

            while (true) {
                // 接收消息
                Message message = consumer.receive();
                if (message instanceof TextMessage) {
                    System.out.println("新闻：" + ((TextMessage) message).getText());
                    // 提交事务
                    session.commit();
                } else {
                    System.out.println("Unexpected message type: " + message.getClass());
                }
            }
        } catch (JMSException e) {
            e.printStackTrace();
        } finally {
            if (session != null) {
                try {
                    session.close();
                } catch (JMSException e) {
                    e.printStackTrace();
                }
            }
            // 关闭连接
            ConnectionUtil.closeConnection(connection);
        }
    }
}
