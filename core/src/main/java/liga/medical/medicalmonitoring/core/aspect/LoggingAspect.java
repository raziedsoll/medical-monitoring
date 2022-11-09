package liga.medical.medicalmonitoring.core.aspect;

import liga.medical.dto.ServiceType;
import liga.medical.medicalmonitoring.core.utils.UuidGenerator;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.AfterThrowing;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;

import java.util.Arrays;

@Slf4j
@Aspect
@Component
public class LoggingAspect {

    @Pointcut("@annotation(liga.medical.medicalmonitoring.core.annotations.DbLog)")
    public void dbLogPointCut() { }

    @Before("dbLogPointCut()")
    public void beforeAdvice(JoinPoint joinPoint) {
        String methodName = joinPoint.getSignature().getName();
        System.out.println(methodName);
        String className = joinPoint.getTarget().getClass().toString();
        Object[] array = joinPoint.getArgs();
        log.info("| UUID: " + UuidGenerator.getUuid() + "| Start: | Method: " + className + " : " +
                methodName + "() |" + "Arguments: " + Arrays.deepToString(array) + " | " +
                "System type id: " + ServiceType.Two + " | ");
    }

    @AfterReturning("dbLogPointCut()")
    public void afterAdvice(JoinPoint joinPoint) {
        String methodName = joinPoint.getSignature().getName();
        String className = joinPoint.getTarget().getClass().toString();
        Object[] array = joinPoint.getArgs();
        log.info("| UUID: " + UuidGenerator.getUuid() + "| Finish: | Method: " + className + " : " +
                methodName + "() |" + "Arguments: " + Arrays.deepToString(array) + " | " +
                "System type id: " + ServiceType.Two + " | ");
    }

    @AfterThrowing(value = "dbLogPointCut()", throwing = "exception")
    public void afterReturningAdvice(JoinPoint joinPoint, Throwable exception) {
        String methodName = joinPoint.getSignature().getName();
        String className = joinPoint.getTarget().getClass().toString();
        Object[] array = joinPoint.getArgs();
        log.info("| UUID: " + UuidGenerator.getUuid() + "| Exception: " + exception.getMessage() + " | " +
                "Method: " + className + " : " + methodName + "() | " +
                "Arguments: " + Arrays.deepToString(array) + " | " +
                "System type id: " + ServiceType.Two + " | ");
    }
}
