package pers.cj.framework.common.security.config;

import org.springframework.security.web.session.SessionInformationExpiredEvent;
import org.springframework.security.web.session.SessionInformationExpiredStrategy;
import pers.cj.framework.common.ResponseDto;
import pers.cj.framework.common.util.JsonUtil;

import javax.servlet.ServletException;
import java.io.IOException;

/**
 * @Description 被挤下线的操作
 * @Author chenj
 * @Date 2019/6/10 15:05
 **/
public class CustomExpiredSessionStrategy implements SessionInformationExpiredStrategy {
    @Override
    public void onExpiredSessionDetected(SessionInformationExpiredEvent event) throws IOException, ServletException {
        String json = JsonUtil.toJson(
                new ResponseDto()
                        .setStatus(456)
                        .setMessage("已经另一台机器登录，您被迫下线。" + event.getSessionInformation().getLastRequest()));
        event.getResponse().setContentType("application/json;charset=utf-8");
        event.getResponse().setHeader("Cache-Control", "no-cache");
        event.getResponse().getWriter().print(json);
    }
}
