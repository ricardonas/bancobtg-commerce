package br.com.btg.commerce.log;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@Aspect
public class LogService {

    private static final Logger LOGGER = LoggerFactory.getLogger(LogService.class);

    @Pointcut("execution(* br.com.btg.commerce.service..*(..))")
    public void logMethod(final JoinPoint joinPoint) {
        String methodName = joinPoint.getSignature().getName();
        String className = joinPoint.getTarget().getClass().getName();
        LogService.LOGGER.info("Executing method {} in class {}", methodName, className);
    }

}