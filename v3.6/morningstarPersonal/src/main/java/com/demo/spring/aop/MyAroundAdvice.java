//����֪ͨ��

package com.demo.spring.aop;

import org.aopalliance.intercept.MethodInterceptor;
import org.aopalliance.intercept.MethodInvocation;

public class MyAroundAdvice implements MethodInterceptor {

             public Object invoke(MethodInvocation arg0) throws Throwable {

                             System.out.println("����ִ�У�");
                             System.out.println("����������" + arg0.getClass().getName());
                             System.out.println("Ŀ��������" + arg0.getThis().getClass().getName());
                             System.out.println("����������" + arg0.getArguments().length);
                             System.out.println("Ŀ�귽������" + arg0.getMethod());
                             return arg0.proceed();
             }
}