package com.lc.listener;

import org.springframework.stereotype.Component;

import javax.jms.JMSException;
import javax.jms.Message;
import javax.jms.MessageListener;
import javax.jms.TextMessage;

@Component
public class QueueListener implements MessageListener {
    public void onMessage(Message message) {
        if (message instanceof TextMessage){
            TextMessage textMessage=(TextMessage)message;
            try {
                System.out.println("spring_consumer_queue:"+textMessage.getText());
            }catch (JMSException e){
                e.printStackTrace();
            }
        }
    }
}
