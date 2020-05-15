package com.lc.consumer;

import org.apache.xbean.spring.context.ClassPathXmlApplicationContext;
public class SpringConsumer {

    public static void main(String[] args) throws Exception{
        ClassPathXmlApplicationContext context=new ClassPathXmlApplicationContext("classpath:bean.xml");
        context.start();
        System.in.read();
    }
}
