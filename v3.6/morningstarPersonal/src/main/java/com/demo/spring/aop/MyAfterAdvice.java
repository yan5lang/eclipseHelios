//返回后通知：

package com.demo.spring.aop;

import java.lang.reflect.Method;
import org.springframework.aop.AfterReturningAdvice;

public class MyAfterAdvice implements AfterReturningAdvice {

               public void afterReturning(Object returnVal, Method arg1, Object[] args, Object target) throws Throwable {
                        System.out.println("END!");
               }
}