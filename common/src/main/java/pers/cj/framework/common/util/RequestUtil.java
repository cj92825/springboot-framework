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
    /**
     * 获取HttpServletRequest对象
     * @return
     */
    public static HttpServletRequest getRequest(){
        ServletRequestAttributes servletRequestAttributes = (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
        return servletRequestAttributes.getRequest();
    }

    /**
     * 获取url
     * @return
     */
    public static String getRequestURI(){
        return getRequest().getRequestURI();
    }

    /**
     * 判断是否是Ajax请求
     * @return
     */
    public static boolean isAjaxRequest(HttpServletRequest request) {
        String ajaxFlag = request.getHeader("X-Requested-With");
        return ajaxFlag != null && "XMLHttpRequest".equals(ajaxFlag);
    }
}
