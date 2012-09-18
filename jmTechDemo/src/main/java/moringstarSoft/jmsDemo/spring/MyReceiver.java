package moringstarSoft.jmsDemo.spring;

import javax.jms.Destination;
import javax.jms.JMSException;
import javax.jms.TextMessage;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.jms.core.JmsTemplate;

/** 
* 消息接收者 
* 
* @author leizhimin 2009-8-13 17:02:04 
*/ 
public class MyReceiver { 
        public static void main(String[] args) throws JMSException { 
                ApplicationContext ctx = new ClassPathXmlApplicationContext("/moringstarSoft/jmsDemo/spring/applicationContext.xml"); 
                JmsTemplate template = (JmsTemplate) ctx.getBean("jmsTemplate"); 
                Destination destination = (Destination) ctx.getBean("destination"); 
                while (true) { 
                        TextMessage txtmsg = (TextMessage) template.receive(destination); 
                        if (null != txtmsg) 
                                System.out.println("收到消息内容为: " + txtmsg.getText()); 
                        else 
                                break; 
                } 
        } 
}
