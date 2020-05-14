package com.lc.productor;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.jms.core.MessageCreator;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import javax.jms.JMSException;
import javax.jms.Message;
import javax.jms.Session;
import javax.jms.TextMessage;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(value = "classpath:bean.xml")
public class SpringProductor {

    @Autowired
    @Qualifier("jmsQueueTemplate")
    JmsTemplate jmsQueueTemplate;

    @Autowired
    @Qualifier("jmsTopicTemplate")
    JmsTemplate jmsTopicTemplate;

    @Test
    public void ptpSender(){
        /*第一个参数名称:  指定队列的名称
          第二个参数名称:  MessageCreator接口
        * */
        jmsQueueTemplate.send("spring_queue", new MessageCreator() {
            public Message createMessage(Session session) throws JMSException {
                TextMessage textMessage = session.createTextMessage("spring_productor queue");
                return textMessage;
            }
        });
    }

    @Test
    public void psSender(){
        jmsTopicTemplate.send("spring_topic", new MessageCreator() {
            public Message createMessage(Session session) throws JMSException {
                TextMessage textMessage = session.createTextMessage("spring_productor topic");
                return textMessage;
            }
        });
    }
}
