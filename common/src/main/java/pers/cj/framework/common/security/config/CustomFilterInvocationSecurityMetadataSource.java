package pers.cj.framework.common.security.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.ConfigAttribute;
import org.springframework.security.access.SecurityConfig;
import org.springframework.security.web.FilterInvocation;
import org.springframework.security.web.access.intercept.FilterInvocationSecurityMetadataSource;
import org.springframework.stereotype.Service;
import org.springframework.util.AntPathMatcher;
import pers.cj.framework.orm.entity.customEntity.UrlResource;
import pers.cj.framework.orm.service.ISysPermissionService;
import pers.cj.framework.orm.service.ISysRolePermissionService;
import pers.cj.framework.orm.service.ISysRoleService;

import java.util.Collection;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * @Description 动态获取url权限配置
 * @Author chenj
 * @Date 2019/6/11 15:49
 **/
@Service
public class CustomFilterInvocationSecurityMetadataSource implements FilterInvocationSecurityMetadataSource {
    @Autowired
    ISysPermissionService iSysPermissionService;

    @Autowired
    ISysRolePermissionService iSysRolePermissionService;

    @Autowired
    ISysRoleService iSysRoleService;

    AntPathMatcher antPathMatcher=new AntPathMatcher();
    @Override
    public Collection<ConfigAttribute> getAttributes(Object object) throws IllegalArgumentException {
        Set<ConfigAttribute> set = new HashSet<>();
        // 获取请求地址
        String requestUrl = ((FilterInvocation) object).getRequest().getServletPath();
        //获取url对应的角色名
        List<UrlResource> resources=getRoleByUrl();
        for (UrlResource resource:resources){
            if(antPathMatcher.match(resource.getUrl(),requestUrl)) {
                ConfigAttribute ca = new SecurityConfig(resource.getRoleName());
                set.add(ca);
            }
        }
        //没有匹配上的资源，都是登录访问
        SecurityConfig.createList("ROLE_LOGIN");
        return set;
    }

    private List<UrlResource>  getRoleByUrl() {
        return iSysRoleService.getRoleByUrl();
    }

    @Override
    public Collection<ConfigAttribute> getAllConfigAttributes() {
        Set<ConfigAttribute> allAttributes = new HashSet<>();
        return allAttributes;
    }

    /**
     * 如果为真则说明支持当前格式类型,才会到上面的 getAttributes 方法中
     */
    @Override
    public boolean supports(Class<?> clazz) {
        return FilterInvocation.class.isAssignableFrom(clazz);
    }
}
