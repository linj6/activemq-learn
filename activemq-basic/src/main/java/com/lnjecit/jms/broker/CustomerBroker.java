package com.lnjecit.jms.broker;

import org.apache.activemq.broker.BrokerService;

/**
 * @author lnj
 * @description 自定义 Broker
 * @date 2019-01-03 14:59
 **/
public class CustomerBroker {
    public static void main(String[] args) {
        BrokerService brokerService = new BrokerService();
        try {
            brokerService.setUseJmx(true);
            brokerService.addConnector("tcp://localhost:61616");
            brokerService.start();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
