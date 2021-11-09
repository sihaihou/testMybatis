package com.reyco.test.core.aop;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

/**
*@author reyco
*@date  2021年1月11日---下午5:44:17
*<pre>
*
*<pre> 
*/
@Aspect
@Component
@Order(-1)
public class TestAop {
	protected Logger logger = LoggerFactory.getLogger(getClass());

	@Pointcut("execution(* com.reyco.test.core.service.*.*(..))")
	public void testPointCut() {
		
	}

	@Around("testPointCut()")
	public Object around(ProceedingJoinPoint point) throws Throwable {
		System.out.println("----------------------");
		return point.proceed();
	}
}
