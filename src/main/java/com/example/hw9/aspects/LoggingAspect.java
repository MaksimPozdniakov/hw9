package com.example.hw9.aspects;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;
import java.time.LocalDateTime;
import java.util.Arrays;

@Aspect
@Component
public class LoggingAspect {
    @Pointcut("@annotation(TrackUserAction)")
    public void trackUserAction() {

    }

    @AfterReturning(pointcut = "trackUserAction()", returning = "result")
    public void logUserAction(JoinPoint joinPoint, Object result) {
        String methodName = joinPoint.getSignature().getName();
        String className = joinPoint.getTarget().getClass().getSimpleName();
        Object[] args = joinPoint.getArgs();
        LocalDateTime timestamp = LocalDateTime.now();
        System.out.println("Метод: " + className + "." + methodName +
                ", вызывается с аргументами: " + Arrays.toString(args) +
                ", время вызова: " + timestamp);
    }
}
