package pers.cj.framework.api.aop;

import java.lang.annotation.*;

/**
 * @Description 自顶住注解记录日志
 * @Author chenj
 * @Date 2019/6/11 14:30
 **/
@Target(ElementType.METHOD)
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface AnnotationLog {
    String value() default "";
}
