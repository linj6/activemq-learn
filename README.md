# ActiveMQ学习笔记

## activemq-basic

1. 点对点模式
   - 每个消息只有一个消费者，即每一条消息只能被消费一次
2. 发布/订阅模式
   - 每一条消息可以被所有订阅者消费
3. 自定义Broker

对于点对点、发布/订阅模式，均提供Message.receive和MessageListener两种接收消息的方式的案例