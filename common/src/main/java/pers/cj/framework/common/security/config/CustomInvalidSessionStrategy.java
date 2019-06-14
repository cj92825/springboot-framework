package pers.cj.framework.common.security.config;

import org.springframework.http.HttpStatus;
import org.springframework.security.web.session.InvalidSessionStrategy;
import org.springframework.stereotype.Component;
import pers.cj.framework.common.model.ResponseDto;
import pers.cj.framework.common.util.JsonUtil;
import pers.cj.framework.common.util.RequestUtil;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * @Description Session无效处理
 * @Author chenj
 * @Date 2019/6/14 10:23
 **/
@Component
public class CustomInvalidSessionStrategy implements InvalidSessionStrategy {
    @Override
    public void onInvalidSessionDetected(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        if(true){
            response.setStatus(HttpStatus.UNAUTHORIZED.value());
            response.setContentType("application/json;charset=utf-8");
            response.setHeader("Cache-Control", "no-cache");
            response.getWriter().print(JsonUtil.toJson(new ResponseDto().setHttpStatus(HttpStatus.UNAUTHORIZED).setMessage("Session已失效,请重新登录")));
        }else{
            response.sendRedirect("/login");
        }
    }
}
