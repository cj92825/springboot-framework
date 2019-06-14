package pers.cj.framework.api.aop;

import com.fasterxml.jackson.core.JsonProcessingException;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.*;
import org.springframework.stereotype.Component;
import pers.cj.framework.common.util.JsonUtil;

import java.util.Arrays;

/**
 * @Description aop日志操作实现类
 * @Author chenj
 * @Date 2019/6/11 14:33
 **/
@Aspect
@Component
@Slf4j
public class AspectLog {

//    @Pointcut("execution(* *..controller.*.*(..))")
    @Pointcut("@annotation(pers.cj.framework.api.aop.AnnotationLog)")
    public void annotationLog(){}

    @Before("annotationLog()")
    public void recordLog(JoinPoint point){
        log.info("method is {}", point.getSignature().getName());
        Object []objects=point.getArgs();
        if(objects!=null&&objects.length>0) {
            try {
                log.info(",Args is {}", JsonUtil.toJson(objects));
            }catch (JsonProcessingException e){

            }
        }
    }

    @AfterReturning(returning = "response", pointcut = "annotationLog()")
    public void doAfterReturning(Object response) throws Throwable {
        if (response != null) {
            log.info("response is {}" , JsonUtil.toJson(response));
        }
    }

}
