//异常通知：

package com.demo.spring.aop;

import java.lang.reflect.Method;
import org.springframework.aop.ThrowsAdvice;

public class MyThrowsAdvice implements ThrowsAdvice {

               public void afterThrowing(Method m, Object[] args, Object target, Exception ex) throws Throwable{
                              System.out.println("有异常抛出！");
               }
}