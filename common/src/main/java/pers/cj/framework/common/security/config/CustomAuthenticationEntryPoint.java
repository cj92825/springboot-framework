package pers.cj.framework.common.security.config;

import org.springframework.http.HttpStatus;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.stereotype.Component;
import pers.cj.framework.common.model.ResponseDto;
import pers.cj.framework.common.util.JsonUtil;
import pers.cj.framework.common.util.RequestUtil;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * @Description 设置未登录是的跳转,原本默认是跳转登录界面
 * 如果是ajax则返回json提示
 * @Author chenj
 * @Date 2019/6/13 14:58
 **/
@Component
public class CustomAuthenticationEntryPoint  implements AuthenticationEntryPoint {
    @Override
    public void commence(HttpServletRequest request, HttpServletResponse response, AuthenticationException authException) throws IOException, ServletException {
        if(RequestUtil.isAjaxRequest(request)){
            response.setStatus(HttpStatus.UNAUTHORIZED.value());
            response.setContentType("application/json;charset=utf-8");
            response.setHeader("Cache-Control", "no-cache");
            response.getWriter().print(JsonUtil.toJson(new ResponseDto().setHttpStatus(HttpStatus.UNAUTHORIZED).setMessage("请登录后进行操作")));
        }else{
            response.sendRedirect("/login");
        }
    }
}
