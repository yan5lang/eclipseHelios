package com.demo.spring.aop;

import java.lang.reflect.Method;
import org.springframework.aop.MethodBeforeAdvice;
//ǰ��֪ͨ
public class MyBeforeAdvice implements MethodBeforeAdvice {

              public void before(Method arg0, Object[] args, Object target) throws Throwable {

                             System.out.println("��������" + arg0.getName());
                             System.out.println("����������" + args.length);
                             System.out.println("Ŀ��������" + target);
                             System.out.println("Start to say:");

              }

}
