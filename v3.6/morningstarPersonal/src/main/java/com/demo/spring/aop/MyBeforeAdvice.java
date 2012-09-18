package com.demo.spring.aop;

import java.lang.reflect.Method;
import org.springframework.aop.MethodBeforeAdvice;
//前置通知
public class MyBeforeAdvice implements MethodBeforeAdvice {

              public void before(Method arg0, Object[] args, Object target) throws Throwable {

                             System.out.println("方法名：" + arg0.getName());
                             System.out.println("参数个数：" + args.length);
                             System.out.println("目标类名：" + target);
                             System.out.println("Start to say:");

              }

}
