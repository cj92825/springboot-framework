package pers.cj.framework.api.config;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.ConversionNotSupportedException;
import org.springframework.beans.TypeMismatchException;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.http.converter.HttpMessageNotWritableException;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.validation.BindException;
import org.springframework.web.HttpMediaTypeNotAcceptableException;
import org.springframework.web.HttpMediaTypeNotSupportedException;
import org.springframework.web.HttpRequestMethodNotSupportedException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.MissingPathVariableException;
import org.springframework.web.bind.MissingServletRequestParameterException;
import org.springframework.web.bind.ServletRequestBindingException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.context.request.async.AsyncRequestTimeoutException;
import org.springframework.web.multipart.support.MissingServletRequestPartException;
import org.springframework.web.servlet.NoHandlerFoundException;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;
import pers.cj.framework.common.ResponseUtil;

/**
 * @Description 异常处理
 * @Author chenj
 * @Date 2019/5/31 16:14
 **/
@Slf4j
@RestControllerAdvice
public class GloablExceptionHandler extends ResponseEntityExceptionHandler {
    /**
     * 通用异常处理,暂时不用
     * @param e
     * @return
     */
//    public Object exceptionHandler(Exception e){
//        log.error("未知异常",e);
//        AccessDeniedException
//        return ResponseUtil.fail();
//    }

    @Override
    protected ResponseEntity<Object> handleExceptionInternal(Exception ex, Object body, HttpHeaders headers, HttpStatus status, WebRequest request) {
        return ResponseUtil.fail(status.value(),ex.getMessage());
    }


    /**
     * 凭证错误异常，这是只做测试
     * @param e
     * @return
     */
//    @ExceptionHandler({BadCredentialsException.class})
//    public Object badCredentialsExceptionHandler(BadCredentialsException e){
//        log.error("登录失败",e);
//        return ResponseUtil.fail(e.getMessage());
//    }
}
