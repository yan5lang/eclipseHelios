package com.demo.spring.test;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.FileSystemXmlApplicationContext;

public class Test {

           public static void main(String[] args) {
                   ApplicationContext ctx = new FileSystemXmlApplicationContext("/config/applicationContext.xml");
                   IHelloWorld hello = (IHelloWorld) ctx.getBean("proxy");
                   try{
                         hello.execute("JOE");
                   }catch(Exception e){
                           e.printStackTrace();
                   } 
          }
}