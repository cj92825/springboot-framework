package pers.cj.framework.common.security.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import pers.cj.framework.orm.entity.SysRole;
import pers.cj.framework.orm.entity.SysUser;
import pers.cj.framework.orm.entity.SysUserRole;
import pers.cj.framework.orm.service.ISysRoleService;
import pers.cj.framework.orm.service.ISysUserRoleService;
import pers.cj.framework.orm.service.ISysUserService;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

/**
 * @Description 自定义UserDetailsService实现,从数据库中获取账户信息
 * @Author chenj
 * @Date 2019/6/4 15:50
 **/
@Service("userDetailsService")
public class CustomUserDetailsService  implements UserDetailsService {

    @Autowired
    ISysUserService iSysUserService;
    @Autowired
    ISysRoleService iSysRoleService;
    @Autowired
    ISysUserRoleService iSysUserRoleService;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Collection<GrantedAuthority> authorities = new ArrayList<>();
        //从数据库查询用户信息
        SysUser sysUser=iSysUserService.getByUserName(username);
        //判断用户是否存在
        if(sysUser==null){
            throw new UsernameNotFoundException("用户名不存在");
        }
        //获取角色信息
        List<SysUserRole> userRoles=iSysUserRoleService.listByUserId(sysUser.getId());
        for (SysUserRole userRole : userRoles) {
            SysRole role = iSysRoleService.getById(userRole.getRoleId());
            authorities.add(new SimpleGrantedAuthority(role.getId().toString()));
        }
        return new User(sysUser.getUsername(),sysUser.getPassword(),authorities);
    }
}
