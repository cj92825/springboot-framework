package pers.cj.framework.common.security.config;

import org.springframework.http.HttpStatus;
import org.springframework.security.web.session.SessionInformationExpiredEvent;
import org.springframework.security.web.session.SessionInformationExpiredStrategy;
import org.springframework.stereotype.Component;
import pers.cj.framework.common.model.ResponseDto;
import pers.cj.framework.common.util.JsonUtil;
import pers.cj.framework.common.util.ServletUtil;

import javax.servlet.ServletException;
import java.io.IOException;

/**
 * @Description 被挤下线的操作
 * @Author chenj
 * @Date 2019/6/10 15:05
 **/
@Component
public class CustomExpiredSessionStrategy implements SessionInformationExpiredStrategy {
    @Override
    public void onExpiredSessionDetected(SessionInformationExpiredEvent event) throws IOException, ServletException {
        if(ServletUtil.isAjaxRequest(event.getRequest())) {
            String json = JsonUtil.toJson(
                    new ResponseDto()
                            .setStatus(HttpStatus.UNAUTHORIZED.value())
                            .setMessage("Session过期或已在另一台机器登录，您被迫下线。" + event.getSessionInformation().getLastRequest()));
            event.getResponse().setContentType("application/json;charset=utf-8");
            event.getResponse().setStatus(HttpStatus.UNAUTHORIZED.value());
            event.getResponse().setHeader("Cache-Control", "no-cache");
            event.getResponse().getWriter().print(json);
        }else{
            event.getResponse().sendRedirect("/login");
        }
    }
}
