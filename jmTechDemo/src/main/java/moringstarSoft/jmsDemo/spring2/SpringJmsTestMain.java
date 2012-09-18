package moringstarSoft.jmsDemo.spring2;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class SpringJmsTestMain {
	 
    public static void main(String[] args) throws InterruptedException {
 
        ApplicationContext context = new ClassPathXmlApplicationContext(
                new String[] { "/moringstarSoft/jmsDemo/spring2/SpringJms.xml" });
 
        SpringPublisher publisher = (SpringPublisher) context
                .getBean("springPublisher");
        publisher.start();
    }
}