package com.board.web.aop;

import java.util.Arrays;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.springframework.stereotype.Component;

@Component
@Aspect
public class ParameterLoggingAdvice {
	
//	@Before("execution(* com.board.web..*Controller.*(..))")
	public void beforeLogging(JoinPoint jp) {
		System.out.println(Arrays.toString(jp.getArgs()));
	}
}
