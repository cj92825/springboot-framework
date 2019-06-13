package pers.cj.framework.common.security.config;

import org.springframework.security.access.AccessDecisionManager;
import org.springframework.security.access.AccessDecisionVoter;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.access.ConfigAttribute;
import org.springframework.security.access.vote.AbstractAccessDecisionManager;
import org.springframework.security.authentication.InsufficientAuthenticationException;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.stereotype.Component;

import java.util.Collection;
import java.util.List;

/**
 * @Description 权限管理器
 * @Author chenj
 * @Date 2019/6/11 16:06
 **/
//@Component
public class CustomAccessDecisionManager  implements AccessDecisionManager {




    @Override
    public void decide(Authentication authentication, Object object, Collection<ConfigAttribute> configAttributes) throws AccessDeniedException, InsufficientAuthenticationException {
        // 获得loadUserByUsername()方法的结果
        User user = (User)authentication.getPrincipal();
        // 获得loadUserByUsername()中注入的角色
        Collection<GrantedAuthority> authorities = user.getAuthorities();
        if(configAttributes==null){
            return;
        }
        for (ConfigAttribute attribute:configAttributes) {
            for (GrantedAuthority authority:authorities){
                if(authority.getAuthority().equals(attribute.getAttribute())){
                    return ;
                }
            }
        }
        throw  new AccessDeniedException("无权访问");
    }

    @Override
    public boolean supports(ConfigAttribute attribute) {
        return true;
    }

    @Override
    public boolean supports(Class<?> clazz) {
        return true;
    }
}
