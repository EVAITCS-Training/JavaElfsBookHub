package com.evaitcsmatt.bookhub.shared.config;

import java.util.Arrays;
import java.util.logging.Level;
import java.util.logging.Logger;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.AfterThrowing;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.springframework.stereotype.Component;


@Aspect
@Component
public class LoggingAspect {
	private static final Logger LOGGER = Logger.getLogger(LoggingAspect.class.getName());
	
	@Before("execution(* com.evaitcsmatt.bookhub.shared.managers.*.*(..))")
	public void logBefore(JoinPoint joinPoint) {
		String messageString =  String.format(
				"Calling Method: {} with args: {}",
				joinPoint.getSignature().getName(),
				Arrays.toString(joinPoint.getArgs())).toString();
		LOGGER.info(messageString);
	}
	
	@AfterReturning(
			pointcut = "execution(* com.evaitcsmatt.bookhub.shared..*.*(..))",
			returning = "result"
			)
	public void logAfterReturn(JoinPoint joinPoint, Object result) {
		String logString = String.format(
				"Method {} returned: {}", 
				joinPoint.getSignature().getName(), 
				result);
		LOGGER.info(logString);
	}
	
	@AfterThrowing(
			pointcut = "execution(* com.evaitcsmatt.bookhub.shared..*.*(..))",
			throwing = "error"
			)
	public void logAfterThrowing(JoinPoint joinPoint, Throwable error) {
		LOGGER.log(Level.SEVERE,
				"Method ".concat(joinPoint.getSignature().getName())
				.concat(" threw exception: ").concat(error.getMessage())
				);
	}
}
