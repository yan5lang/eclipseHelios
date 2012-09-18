package moringstarSoft.jmsDemo.spring;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
// don't successful!
public class TestSpringJms {
	public static void main(String[] args) {
		ApplicationContext ctx = new ClassPathXmlApplicationContext("/moringstarSoft/jmsDemo/spring/applicationContext.xml");
	}

}