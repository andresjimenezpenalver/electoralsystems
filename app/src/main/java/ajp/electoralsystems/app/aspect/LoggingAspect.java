package ajp.electoralsystems.app.aspect;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;

import lombok.extern.slf4j.Slf4j;

/**
 * @author Andres Jimenez Penalver
 */
@Aspect
@Slf4j
public class LoggingAspect {

	public LoggingAspect() {
		log.info("LoggingAspect created");
	}

	@Before("execution(* ajp.electoralsystems.controller.*.*(..))")
	public void logBefore(JoinPoint joinPoint) {
		log.info("before " + joinPoint.toShortString());
	}

	@After("execution(* ajp.electoralsystems.app.controller.*.*(..))")
	public void logAfter(JoinPoint joinPoint) {
		log.info("after " + joinPoint.toShortString());
	}

//	@Around("execution(* ajp.electoralsystems.app.controller.*.*(..))")
//	public void logAround(JoinPoint joinPoint) {
//		log.info("around " + joinPoint.toShortString());
//	}	

}
