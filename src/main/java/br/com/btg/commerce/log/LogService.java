package br.com.btg.commerce.log;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

@Aspect
@Component
public class LogService {

    private static final Logger LOGGER = LoggerFactory.getLogger(LogService.class);

    @Around("execution(* br.com.btg.commerce.service..*(..))")
    public Object logMethod(ProceedingJoinPoint joinPoint) throws Throwable {
        long startTime = System.currentTimeMillis();

        Object result = joinPoint.proceed();

        long endTime = System.currentTimeMillis();
        long executionTime = endTime - startTime;

        String methodName = joinPoint.getSignature().getName();
        String className = joinPoint.getTarget().getClass().getName();
        LogService.LOGGER.info("Method {} in class {} executed in {} ms", methodName, className, executionTime);

        return result;
    }

}