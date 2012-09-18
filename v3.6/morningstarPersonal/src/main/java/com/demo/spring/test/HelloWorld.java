package com.demo.spring.test;

import java.io.IOException;

public class HelloWorld implements IHelloWorld {

           public void execute(String name) throws Exception{
                       System.out.println("Hello World!");
                       //if(true) throw new IOException();
           }

}