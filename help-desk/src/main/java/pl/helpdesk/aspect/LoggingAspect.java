package pl.helpdesk.aspect;

import java.util.logging.Logger;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;

@Aspect
@Component
public class LoggingAspect {

	private Logger logger = Logger.getLogger(getClass().getName());

	@Pointcut("execution(* pl.helpdesk.controller.*.*(..))")
	private void forControllerPackage() {
	}

	@Pointcut("execution(* pl.helpdesk.service.*.*(..))")
	private void forServicePackage() {
	}

	@Pointcut("execution(* pl.helpdesk.dao.*.*(..))")
	private void forDAOPackage() {
	}

	@Pointcut("forControllerPackage() || forServicePackage() || forDAOPackage()")
	private void forAppFlow() {
	}

	@Before("forAppFlow()")
	public void before(JoinPoint joinPoint) {
		String method = joinPoint.getSignature().toShortString();
		logger.info(">> @Before callling method: " + method);
		
		
		/*
	 	Object[] args = joinPoint.getArgs();
		for (Object tempArg : args) {
			logger.info(">> Argument: " + tempArg);
		}
		*/
	}

	@AfterReturning(pointcut = "forAppFlow()", returning = "result")
	public void afterReturning(JoinPoint joinPoint, Object result) {
		String method = joinPoint.getSignature().toShortString();
		logger.info(">> @AfterReturning from method: " + method);
		//logger.info(">> Result: " + result);
	}
}
