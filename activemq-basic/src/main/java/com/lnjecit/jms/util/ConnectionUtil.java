package com.lnjecit.jms.util;

import com.lnjecit.jms.constants.ActiveMQConstants;
import org.apache.activemq.ActiveMQConnectionFactory;

import javax.jms.Connection;
import javax.jms.ConnectionFactory;
import javax.jms.JMSException;

/**
 * @author lnj
 * @description
 * @date 2019-01-03 15:10
 **/
public class ConnectionUtil {

    public static Connection getConnection() throws JMSException {
        // 连接工厂
        ConnectionFactory connectionFactory = new ActiveMQConnectionFactory(ActiveMQConstants.BROKER_URL);
        return connectionFactory.createConnection();
    }

    public static void closeConnection(Connection connection) {
        if (connection != null) {
            try {
                connection.close();
            } catch (JMSException e) {
                e.printStackTrace();
            }
        }
    }
}
