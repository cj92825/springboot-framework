package pers.cj.framework.common.security.config;

import org.springframework.http.HttpStatus;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.web.access.AccessDeniedHandler;
import org.springframework.stereotype.Component;
import pers.cj.framework.common.ResponseDto;
import pers.cj.framework.common.util.JsonUtil;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * @Description 权限异常处理器
 * @Author chenj
 * @Date 2019/6/10 16:26
 **/
@Component
public class CustomAccessDeniedHandler implements AccessDeniedHandler {

    @Override
    public void handle(HttpServletRequest request, HttpServletResponse response, AccessDeniedException accessDeniedException) throws IOException, ServletException {

        response.setStatus(HttpStatus.FORBIDDEN.value());
        response.setContentType("application/json;charset=utf-8");
        response.setHeader("Cache-Control", "no-cache");
        response.getWriter().print(JsonUtil.toJson(new ResponseDto().setHttpStatus(HttpStatus.FORBIDDEN).setMessage(accessDeniedException.getMessage())));
    }
}
