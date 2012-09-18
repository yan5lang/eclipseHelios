package moringstarSoft.jettyDemo.spring;

//创建一个Server类，用来通过spring来启动Jetty server

import org.springframework.context.support.ClassPathXmlApplicationContext;

public class MyServer {  
    public static void main(String[] args) throws Exception {  
        new ClassPathXmlApplicationContext("/moringstarSoft/jettyDemo/spring/spring.xml");  
    }  
} 
