package com.lc.springboot_consumer.listener;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.jms.annotation.JmsListener;
import org.springframework.stereotype.Component;

import javax.jms.JMSException;
import javax.jms.Message;
import javax.jms.TextMessage;

@Component
public class ConsumerListener {

    @JmsListener(destination ="${activemq.name}")
    public void receive(Message message){
        if (message instanceof Message){
            TextMessage textMessage=(TextMessage)message;
            try {
                System.out.println(textMessage.getText());
            }catch (JMSException e){
                e.printStackTrace();
            }
        }
    }
}
