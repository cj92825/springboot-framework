package pers.cj.framework.api.config;

import lombok.extern.slf4j.Slf4j;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;
import pers.cj.framework.api.common.ResponseUtil;

/**
 * @Description 异常处理
 * @Author chenj
 * @Date 2019/5/31 16:14
 **/
@Slf4j
@RestControllerAdvice
//public class GloablExceptionHandler extends ResponseEntityExceptionHandler {
public class GloablExceptionHandler{
    /**
     * 通用异常处理
     * @param e
     * @return
     */
    @ExceptionHandler({Exception.class})
    public Object ExceptionHandler(Exception e){
        log.error("未知异常",e);
        return ResponseUtil.fail();
    }

    /**
     * 凭证错误异常，这是只做测试
     * @param e
     * @return
     */
    @ExceptionHandler({BadCredentialsException.class})
    public Object BadCredentialsExceptionHandler(BadCredentialsException e){
        log.error("登录失败",e);
        return ResponseUtil.fail(e.getMessage());
    }
}
