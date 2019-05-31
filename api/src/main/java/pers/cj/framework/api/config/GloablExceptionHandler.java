package pers.cj.framework.api.config;

import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import pers.cj.framework.api.common.ResponseUtil;

/**
 * @Description 异常处理
 * @Author chenj
 * @Date 2019/5/31 16:14
 **/
@RestControllerAdvice
public class GloablExceptionHandler {
    /**
     * 通用异常处理
     * @param e
     * @return
     */
    @ExceptionHandler({Exception.class})
    public Object ExceptionHandler(Exception e){
        return ResponseUtil.fail();
    }
}
