package com.evaitcsmatt.bookhub.shared.config;

import java.util.Arrays;
import java.util.logging.Logger;

import org.aspectj.lang.JoinPoint;
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
				Arrays.toString(joinPoint.getArgs()));
		LOGGER.info(messageString);
	}
}
