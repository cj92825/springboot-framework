package pers.cj.framework.common.security.config;

import com.alibaba.fastjson.JSON;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.web.DefaultRedirectStrategy;
import org.springframework.security.web.RedirectStrategy;
import org.springframework.security.web.session.InvalidSessionStrategy;
import org.springframework.stereotype.Component;
import pers.cj.framework.common.model.ResponseDto;
import pers.cj.framework.common.util.JsonUtil;
import pers.cj.framework.common.util.ServletUtil;

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
    private final String destinationUrl="/login";
    private final RedirectStrategy redirectStrategy = new DefaultRedirectStrategy();
    private boolean createNewSession = true;
    @Override
    public void onInvalidSessionDetected(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        //参考默认类SimpleRedirectInvalidSessionStrategy里的方法，创建一个新的session
        if (createNewSession) {
            request.getSession();
        }
        if(ServletUtil.isAjaxRequest(request)){
            response.setStatus(HttpStatus.UNAUTHORIZED.value());
            response.setContentType("application/json;charset=utf-8");
            response.setHeader("Cache-Control", "no-cache");
            response.getWriter().print(JsonUtil.toJson(new ResponseDto().setHttpStatus(HttpStatus.UNAUTHORIZED).setMessage("Session已失效,请重新登录")));
        }else{
            redirectStrategy.sendRedirect(request, response, destinationUrl);
        }
    }
}
