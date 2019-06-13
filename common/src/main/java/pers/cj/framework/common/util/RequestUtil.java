package pers.cj.framework.common.util;

import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;

/**
 * @Description Request工具类
 * @Author chenj
 * @Date 2019/6/11 9:57
 **/
public class RequestUtil {
    public static HttpServletRequest getRequest(){
        ServletRequestAttributes servletRequestAttributes = (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
        return servletRequestAttributes.getRequest();
    }
    public static String getRequestURI(){
        return getRequest().getRequestURI();
    }
}
