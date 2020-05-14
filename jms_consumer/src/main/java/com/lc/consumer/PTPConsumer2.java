package com.lc.consumer;

import org.apache.activemq.ActiveMQConnectionFactory;
import javax.jms.*;

// 原生jms -- 点对点 -- 消息消费者 --（异步）
public class PTPConsumer2 {
    public static void main(String[] args) throws JMSException {
//   1  创建链接地址
        ConnectionFactory factory=new ActiveMQConnectionFactory("tcp://localhost:61616");
//   2  创建连接
//   3  打开链接
        Connection connection = factory.createConnection();
        connection.start();
//   4  创建Session
        Session session = connection.createSession(false,Session.AUTO_ACKNOWLEDGE);
//   5 指定目标地址
        Queue queue = session.createQueue("queue-01");
//   6 创建消息消费者
        MessageConsumer consumer = session.createConsumer(queue);
//   7 接收消息(异步)
        consumer.setMessageListener(new MessageListener() {
            public void onMessage(Message message) {
                if (message instanceof TextMessage){
                    TextMessage textMessage=(TextMessage) message;
                    try {
                        System.out.println("consumer2:  "+textMessage.getText());
                    }catch (JMSException e){
                        e.printStackTrace();
                    }
                }
            }
        });

    }
}
