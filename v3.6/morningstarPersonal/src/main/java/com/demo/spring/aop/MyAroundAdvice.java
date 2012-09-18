//环绕通知：

package com.demo.spring.aop;

import org.aopalliance.intercept.MethodInterceptor;
import org.aopalliance.intercept.MethodInvocation;

public class MyAroundAdvice implements MethodInterceptor {

             public Object invoke(MethodInvocation arg0) throws Throwable {

                             System.out.println("正在执行！");
                             System.out.println("代理类名：" + arg0.getClass().getName());
                             System.out.println("目标类名：" + arg0.getThis().getClass().getName());
                             System.out.println("参数个数：" + arg0.getArguments().length);
                             System.out.println("目标方法名：" + arg0.getMethod());
                             return arg0.proceed();
             }
}