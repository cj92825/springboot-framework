package pers.cj.framework.web.sys;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiOperation;
import org.springframework.security.access.ConfigAttribute;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.web.bind.annotation.*;
import pers.cj.framework.common.ResponseUtil;
import pers.cj.framework.common.exception.CustomException;
import pers.cj.framework.common.security.model.CustomUser;
import pers.cj.framework.orm.entity.SysUser;

import java.util.Collection;

/**
 * @Description TODO
 * @Author chenj
 * @Date 2019/6/22 16:49
 **/
@Api(value = "用户相关的接口")
@RestController
@RequestMapping("/user")
public class UserController {
    private Authentication authentication;

    @ApiOperation(value="查询当前用户信息")
    @GetMapping("/info")
    public Object userInfo() throws CustomException {
        UsernamePasswordAuthenticationToken authentication= (UsernamePasswordAuthenticationToken)SecurityContextHolder.getContext().getAuthentication();
        String name=authentication.getName();
        Collection<? extends GrantedAuthority> authorities =authentication.getAuthorities();
//        String pwd=authentication.getCredentials().toString();
        CustomUser user=(CustomUser) authentication.getPrincipal();
        SysUser user1=user.getSysUser();
//        UserDetails userDetails= (UserDetails)authentication.getPrincipal();
//        String name2=userDetails.getUsername();
//        Collection<? extends GrantedAuthority> authorities2=userDetails.getAuthorities();
        return ResponseUtil.success();
    }
}
