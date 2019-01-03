package com.lnjecit.jms.producer;

import com.lnjecit.jms.util.ConnectionUtil;

import javax.jms.*;

/**
 * @author lnj
 * @description activemq 发布/订阅模式 消息发送方
 * @date 2019-01-03 14:26
 **/
public class JmsPublisher {
    public static void main(String[] args) {
        // 获取连接
        Connection connection = null;

        Session session = null;
        try {
            connection = ConnectionUtil.getConnection();

            // 启动连接
            connection.start();

            // 开启事务性会话，消息在消费端执行 session.commit 后自动被签收
            session = connection.createSession(true, Session.AUTO_ACKNOWLEDGE);

            // 创建队列
            Destination destination = session.createTopic("news-topic");

            // 创建生产者
            MessageProducer producer = session.createProducer(destination);

            // 创建消息
            TextMessage textMessage = session.createTextMessage("秦时明月第六季终于更新了！");

            // 发送消息
            producer.send(textMessage);

            // 提交事务
            session.commit();
        } catch (JMSException e) {
            e.printStackTrace();
        } finally {
            // 关闭会话
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
