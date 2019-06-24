package pers.cj.framework.common.security.model;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.User;
import pers.cj.framework.orm.entity.SysUser;

import java.util.Collection;

/**
 * @Description 继承User类，将sysUser也放进去，方便读取用户信息
 * @Author chenj
 * @Date 2019/6/24 10:43
 **/
public class CustomUser extends User {
    private SysUser sysUser;
    public CustomUser(String username, String password, Collection<? extends GrantedAuthority> authorities) {
        super(username, password, authorities);
    }

    public CustomUser(String username, String password, boolean enabled, boolean accountNonExpired, boolean credentialsNonExpired, boolean accountNonLocked, Collection<? extends GrantedAuthority> authorities) {
        super(username, password, enabled, accountNonExpired, credentialsNonExpired, accountNonLocked, authorities);
    }

    public CustomUser(Collection<? extends GrantedAuthority> authorities,SysUser sysUser){
        super(sysUser.getUsername(), sysUser.getPassword(), authorities);
        this.sysUser=sysUser;
    }

    public SysUser getSysUser() {
        return sysUser;
    }

    public void setSysUser(SysUser sysUser) {
        this.sysUser = sysUser;
    }
}
