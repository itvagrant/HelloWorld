package com.srpingdemo.day1.aspect;

import org.aspectj.lang.ProceedingJoinPoint;

/**
 * 切面类
 * @author bwfadmin
 *
 */
public class MyAspect {
	
	public MyAspect(){
		System.out.println("=================MyAspect创建了=================");
	}
	
	public void doBefore(){
		System.out.println("MyAspect===doBefore()");
	}
	
	public void doAfter(){
		System.out.println("MyAspect===doAfter()");
	}
	
	public void doAfterReturning(){
		System.out.println("MyAspect===doAfterReturning()");
	}
	
	public void doAfterThrowing(){
		System.out.println("MyAspect===doAfterThrowing()");
	}
	
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
