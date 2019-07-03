package pers.cj.framework.common.security.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.PermissionEvaluator;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.stereotype.Component;
import pers.cj.framework.orm.entity.SysPermission;
import pers.cj.framework.orm.service.ISysPermissionService;
import pers.cj.framework.orm.service.ISysRoleService;

import java.io.Serializable;
import java.util.Arrays;
import java.util.Collection;
import java.util.List;

/**
 * @Description 权限鉴别器，基于hasPermission注解的，不用了
 * @Author chenj
 * @Date 2019/6/10 11:38
 **/
//@Component
//public class CustonPermissionEvaluator  implements PermissionEvaluator {
//
//    @Autowired
//    ISysRoleService iSysRoleService;
//    @Autowired
//    ISysPermissionService iSysPermissionService;
//
//    @Override
//    public boolean hasPermission(Authentication authentication, Object targetUrl, Object targetPermission) {
//        // 获得loadUserByUsername()方法的结果
//        User user = (User)authentication.getPrincipal();
//        // 获得loadUserByUsername()中注入的角色
//        Collection<GrantedAuthority> authorities = user.getAuthorities();
//        // 遍历permissionList
//        // 遍历用户所有角色
//        for(GrantedAuthority authority : authorities) {
//            String roleName = authority.getAuthority();
////            Integer roleId = iSysRoleService.selectByName(roleName).getId();
//            // 得到角色所有的权限
//            List<SysPermission> permissionList = iSysPermissionService.listByRoleName(roleName);
//
//            // 遍历permissionList
//            for(SysPermission sysPermission : permissionList) {
//                // 获取权限集
//                List permissions = Arrays.asList(sysPermission.getPermission().trim().split(","));
//                // 如果访问的Url和权限用户符合的话，返回true
//                if(targetUrl.equals(sysPermission.getUrl())
//                        && permissions.contains(targetPermission)) {
//                    return true;
//                }
//            }
//        }
//        return false;
//    }
//
//    @Override
//    public boolean hasPermission(Authentication authentication, Serializable targetId, String targetType, Object permission) {
//        return false;
//    }
//}
