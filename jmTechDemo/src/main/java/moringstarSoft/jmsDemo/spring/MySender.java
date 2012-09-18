package moringstarSoft.jmsDemo.spring;

import javax.jms.Destination;
import javax.jms.JMSException;
import javax.jms.Message;
import javax.jms.Session;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.jms.core.MessageCreator;

/** 
* 消息发送者 
* 
* @author leizhimin 2009-8-13 17:01:48 
*/ 
public class MySender { 
        public static void main(String[] args) { 
                ApplicationContext ctx = new ClassPathXmlApplicationContext("/moringstarSoft/jmsDemo/spring/applicationContext.xml"); 
                JmsTemplate template = (JmsTemplate) ctx.getBean("jmsTemplate"); 
                Destination destination = (Destination) ctx.getBean("destination"); 

                template.send(destination, new MessageCreator() { 
                        public Message createMessage(Session session) throws JMSException { 
                                return session.createTextMessage("发送消息：Hello ActiveMQ Text Message！"); 
                        } 
                }); 
                System.out.println("成功发送了一条JMS消息"); 
        } 
}
