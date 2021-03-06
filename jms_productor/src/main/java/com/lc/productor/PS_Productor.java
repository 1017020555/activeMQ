package com.lc.productor;

import org.apache.activemq.ActiveMQConnectionFactory;

import javax.jms.*;

/*  原生jms --    发布订阅模式---消息生产者
*/
public class PS_Productor {
    public static void main(String[] args) throws JMSException {
        //  1创建连接工厂
        ConnectionFactory factory=new ActiveMQConnectionFactory("tcp://localhost:61616");
        //  2创建连接
        //  3打开连接
        Connection connection = factory.createConnection();
        connection.start();
        // 4 创建session
        /*      参数1：是否打开事务
                参数2：消息确认机制
         */
        Session session = connection.createSession(false,Session.AUTO_ACKNOWLEDGE);

        // 5 创建目标地址
        Topic topic = session.createTopic("topic-01");
//        Queue queue = session.createQueue("queue-01");

        //   6 创建消息生产者
        MessageProducer producer = session.createProducer(topic);
        //  7  创建消息
        TextMessage message = session.createTextMessage("productor--测试topic");
        //   8 发送消息
        producer.send(message);
        //   9 释放资源
        session.close();
    }
}
