package pers.cj.framework.common.security.config;

import org.springframework.http.HttpStatus;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.logout.LogoutSuccessHandler;
import org.springframework.stereotype.Component;
import pers.cj.framework.common.model.ResponseDto;
import pers.cj.framework.common.util.JsonUtil;
import pers.cj.framework.common.util.RequestUtil;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * @Description 注销处理handler,如果是ajax则返回json,否则跳转到登录界面
 * @Author chenj
 * @Date 2019/6/13 15:18
 **/
@Component
public class CustomLogoutSuccessHandler implements LogoutSuccessHandler {
    @Override
    public void onLogoutSuccess(HttpServletRequest request, HttpServletResponse response, Authentication authentication) throws IOException, ServletException {
        if(RequestUtil.isAjaxRequest(request)){
            response.setStatus(HttpStatus.OK.value());
            response.setContentType("application/json;charset=utf-8");
            response.setHeader("Cache-Control", "no-cache");
            response.getWriter().print(JsonUtil.toJson(new ResponseDto().setMessage("退出登录成功")));
        }else{
            response.sendRedirect("/login");
        }
    }
}
