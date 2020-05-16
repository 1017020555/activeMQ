package com.lc.springboot_productor;
import com.lc.springboot_productor.pojo.User;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.jms.core.JmsMessagingTemplate;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.jms.core.MessageCreator;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import javax.jms.*;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest(classes = SpringbootProductorApplication.class)
public class Productor {

    @Autowired
    JmsTemplate jmsTemplate;
    @Autowired
    JmsMessagingTemplate jmsMessagingTemplate;

    @Value("${activemq.name}")
    String name;

    @Test
    public void send(){
        jmsMessagingTemplate.convertAndSend(name,"queue");
    }

//    测试消息头：
    @Test
    public void sendd(){
        jmsTemplate.send(name, new MessageCreator() {
            @Override
            public Message createMessage(Session session) throws JMSException {
                TextMessage textMessage = session.createTextMessage();
                textMessage.setText("设置消息头测试--");
                textMessage.setJMSMessageID("11111");
                textMessage.setJMSPriority(9);
                textMessage.setJMSCorrelationID("22222");
                return textMessage;
            }
        });
    }

//  消息类型 map ,object ,stream bytes
    @Test
    public void type(){

        jmsTemplate.send(name, new MessageCreator() {
            @Override
            public Message createMessage(Session session) throws JMSException {
//                MapMessage message = session.createMapMessage();
//                message.setInt("a",1);
//                message.setString("b","map");

//                User user=new User();
//                user.setUsername("abc");
//                user.setPassword("123");
//                ObjectMessage message = session.createObjectMessage();
//                message.setObject(user);

                StreamMessage message = session.createStreamMessage();
                message.setBooleanProperty("a",true);
                message.setIntProperty("b",11);

                message.setStringProperty("test","test");

                return message;
            }
        });
    }

}
