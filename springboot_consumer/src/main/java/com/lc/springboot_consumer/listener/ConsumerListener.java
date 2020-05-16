package com.lc.springboot_consumer.listener;

import com.lc.springboot_consumer.pojo.User;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.jms.annotation.JmsListener;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import javax.jms.*;
import java.util.Map;

@Component
public class ConsumerListener {

    @JmsListener(destination ="${activemq.name}")
    public void receive(Message message){
        if (message instanceof TextMessage){
            TextMessage textMessage=(TextMessage)message;
            try {
                System.out.println("JMSCorrelationID:"+textMessage.getJMSCorrelationID());

                System.out.println("text: "+textMessage.getText());
            }catch (JMSException e){
                e.printStackTrace();
            }
        }else if (message instanceof MapMessage){
            MapMessage message1=(MapMessage)message;
            try {
                System.out.println("a: "+message1.getInt("a")+" ,b: "+message1.getString("b"));
            }catch (JMSException e){
                e.printStackTrace();
            }
        }else if (message  instanceof  ObjectMessage){
            ObjectMessage objectMessage=(ObjectMessage)message;
            try {
                User user=(User)objectMessage.getObject();
                System.out.println(user);
            }catch (JMSException e){
                e.printStackTrace();
            }
        }else if(message instanceof StreamMessage){
            try {
                StreamMessage streamMessage=(StreamMessage)message;
                boolean a = streamMessage.getBooleanProperty("a");
                int b = streamMessage.getIntProperty("b");
                System.out.println(a+"---"+b);

                String test = message.getStringProperty("test");
                System.out.println("test:"+test);

            }catch (JMSException e){
                e.printStackTrace();
            }
        }

    }

}
