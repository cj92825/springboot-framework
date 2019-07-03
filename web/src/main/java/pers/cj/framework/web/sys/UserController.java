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

import java.util.*;
import java.util.stream.Collectors;

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
    public Object userInfo(){
        Authentication authentication= SecurityContextHolder.getContext().getAuthentication();
        Map<String,Object> map=new HashMap<>(5);
        map.put("name",authentication.getName());
        Collection<? extends GrantedAuthority > grantedAuthorities = authentication.getAuthorities();
        List<String> roles=grantedAuthorities.stream().map(GrantedAuthority::getAuthority).collect(Collectors.toList());
        map.put("roles",roles);
        return ResponseUtil.success(map);
    }
}
