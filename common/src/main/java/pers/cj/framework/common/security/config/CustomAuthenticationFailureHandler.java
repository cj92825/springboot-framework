package pers.cj.framework.common.security.config;

import com.alibaba.fastjson.JSON;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.authentication.AccountStatusException;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.AuthenticationFailureHandler;
import org.springframework.stereotype.Component;
import pers.cj.framework.common.ResponseDto;
import pers.cj.framework.common.ResponseUtil;
import pers.cj.framework.common.security.util.JsonUtil;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * @Description 登录失败处理器，全部返回json信息
 * @Author chenj
 * @Date 2019/6/6 10:46
 **/
@Slf4j
@Component
public class CustomAuthenticationFailureHandler implements AuthenticationFailureHandler {
    @Override
    public void onAuthenticationFailure(HttpServletRequest request, HttpServletResponse response, AuthenticationException exception) throws IOException, ServletException {
        log.info("登录失败{}",exception);
        response.setContentType("application/json;charset=utf-8");
        response.setHeader("Cache-Control", "no-cache");
        response.setStatus(HttpStatus.UNAUTHORIZED.value());
        response.getWriter().print(JsonUtil.toJson(new ResponseDto().setCode(HttpStatus.UNAUTHORIZED.value()).setMsg("登录失败" + exception.getMessage())));
    }
}
