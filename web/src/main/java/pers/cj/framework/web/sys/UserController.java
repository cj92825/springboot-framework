package pers.cj.framework.web.sys;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.github.shihyuho.jackson.databind.SerializeAllExcept;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.ConfigAttribute;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.web.bind.annotation.*;
import pers.cj.framework.api.service.sys.SysService;
import pers.cj.framework.common.ResponseUtil;
import pers.cj.framework.common.exception.CustomException;
import pers.cj.framework.common.security.model.CustomUser;
import pers.cj.framework.orm.entity.SysUser;
import pers.cj.framework.orm.service.ISysUserService;

import java.util.*;
import java.util.stream.Collectors;

/**
 * @Description TODO
 * @Author chenj
 * @Date 2019/6/22 16:49
 **/
@Api(value = "用户相关的接口")
@RestController
public class UserController {
    @Autowired
    SysService sysService;

    @Autowired
    ISysUserService iSysUserService;

    @ApiOperation(value="新增用户",notes = "account，password必填，其他选填",response = Integer.class)
    @ApiImplicitParam(name = "sysUser", value = "用户信息", required = true, dataType = "SysUser",paramType = "body")
    @PostMapping("/sys/editUser")
    public Object editUser(@RequestBody SysUser sysUser) throws CustomException {
        Long userId=sysService.editUser(sysUser);
        return ResponseUtil.success(userId);
    }
    @ApiOperation(value="删除用户",notes = "传入用户id",response = Boolean.class)
    @ApiImplicitParam(name = "userId", value = "用户Id", required = true, dataType = "int",paramType = "body")
    @PostMapping("/sys/deleteUser")
    public Object deleteUser(@RequestBody Integer userId){
        return ResponseUtil.success( iSysUserService.removeById(userId));
    }

    @ApiOperation(value="查询用户列表分页返回",notes = "传入当前页，每页数量",response = IPage.class)
    @ApiImplicitParams({
            @ApiImplicitParam(name = "currentPage", value = "当前页", required = true, dataType = "long",paramType = "query"),
            @ApiImplicitParam(name = "size", value = "每页size", required = true, dataType = "long",paramType = "query")
    })
    @SerializeAllExcept({"password"})
    @GetMapping("/sys/users")
    public Object queryUsers(@RequestParam("currentPage") long currentPage,@RequestParam("size") long size){
        return ResponseUtil.success(iSysUserService.page(new Page<>(currentPage,size)));
    }

    @ApiOperation(value="查询当前用户信息")
    @GetMapping("/user/info")
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
