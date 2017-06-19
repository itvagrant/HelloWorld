package com.srpingdemo.day1.aspect;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.AfterThrowing;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;

/**
 * 切面类
 * @author bwfadmin
 *
 */
@Component
@Aspect
public class MyAspect2 {
	
	@Pointcut("execution(* com.cd.mvc.service.*.*(..))")
	public void aspect(){}
	
	@Before("aspect()")
	public void doBefore(){
		System.out.println("MyAspect===doBefore()");
	}
	@After("aspect()")
	public void doAfter(){
		System.out.println("MyAspect===doAfter()");
	}
	@AfterReturning("aspect()")
	public void doAfterReturning(){
		System.out.println("MyAspect===doAfterReturning()");
	}
	@AfterThrowing("aspect()")
	public void doAfterThrowing(){
		System.out.println("MyAspect===doAfterThrowing()");
	}
	
	@Around("aspect()")
	public void doAround(ProceedingJoinPoint pjp){
		
		try {
			System.out.println("MyAspect===doAround()>>>>before");
			pjp.proceed();
		} catch (Throwable e) {
			e.printStackTrace();
		}
		System.out.println("MyAspect===doAround()>>>>after");
	}
}
