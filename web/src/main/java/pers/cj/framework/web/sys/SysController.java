package pers.cj.framework.web.sys;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.core.toolkit.StringUtils;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import pers.cj.framework.api.service.sys.SysService;
import pers.cj.framework.common.ResponseUtil;
import pers.cj.framework.common.exception.CustomException;
import pers.cj.framework.orm.entity.*;
import pers.cj.framework.orm.service.*;

/**
 * @Description 系统管理相关的接口
 * @Author chenj
 * @Date 2019/6/12 11:08
 **/
@Api(value = "系统管理相关的接口")
@RestController
@RequestMapping("/sys")
public class SysController {

    @Autowired
    SysService sysService;
    @Autowired
    ISysUserService iSysUserService;
    @Autowired
    ISysRoleService iSysRoleService;
    @Autowired
    ISysUserRoleService iSysUserRoleService;
    @Autowired
    ISysRolePermissionService iSysRolePermissionService;
    @Autowired
    ISysPermissionService iSysPermissionService;

    @ApiOperation(value="新增用户",notes = "account，password必填，其他选填",response = Integer.class)
    @ApiImplicitParam(name = "sysUser", value = "用户信息", required = true, dataType = "SysUser",paramType = "body")
    @PostMapping("/addUser")
    public Object addUser(@RequestBody SysUser sysUser) throws CustomException {
        Long userId=sysService.addUser(sysUser);
        return ResponseUtil.success(userId);
    }


    @ApiOperation(value="删除用户",notes = "传入用户id",response = Boolean.class)
    @ApiImplicitParam(name = "userId", value = "用户Id", required = true, dataType = "int",paramType = "body")
    @PostMapping("/deleteUser")
    public Object deleteUser(@RequestBody Integer userId){
        return ResponseUtil.success( iSysUserService.removeById(userId));
    }

    @ApiOperation(value="新增角色",response = Integer.class)
    @ApiImplicitParam(name = "sysRole", value = "用户信息", required = true, dataType = "sysRole",paramType = "body")
    @PostMapping("/addRole")
    public Object addRole(@RequestBody SysRole sysRole) throws CustomException {

        return ResponseUtil.success(sysService.addRole(sysRole));
    }

    @ApiOperation(value="查询用户列表分页返回",notes = "传入当前页，每页数量",response = IPage.class)
    @ApiImplicitParams({
            @ApiImplicitParam(name = "currentPage", value = "当前页", required = true, dataType = "long",paramType = "query"),
            @ApiImplicitParam(name = "size", value = "每页size", required = true, dataType = "long",paramType = "query")
    })
    @GetMapping("/queryUsers")
    public Object queryUsers(@RequestParam("currentPage") long currentPage,@RequestParam("size") long size){
        return ResponseUtil.success(iSysUserService.page(new Page<>(currentPage,size)));
    }

    @ApiOperation(value="查询角色列表分页返回",notes = "传入当前页，每页数量",response = IPage.class)
    @GetMapping("/queryRoles")
    public Object queryRoles(@RequestParam("currentPage") long currentPage,
                             @RequestParam("size") long size,
                             @RequestParam(value = "user_id",required = false) Long id){
        return ResponseUtil.success(
                iSysRoleService.page(
                        new Page<>(currentPage,size),
                         new QueryWrapper<SysRole>().eq(id!=null,"user_id",id)
                ));
    }

    @ApiOperation(value="查询资源列表分页返回",notes = "传入当前页，每页数量,",response = IPage.class)
    @GetMapping("/queryPermissions")
    public Object queryPermissions(@RequestParam("currentPage") long currentPage,
                                   @RequestParam("size") long size,
                                   @RequestParam(value = "role_id",required = false) Long id){
        return ResponseUtil.success(
                iSysPermissionService.page(
                        new Page<>(currentPage,size),
                        new QueryWrapper<SysPermission>().eq(id!=null,"role_id",id)
                ));
    }

    @ApiOperation(value="赋予用户角色",notes = "传入用户id，角色id",response = Boolean.class)
    @PostMapping("/grantRole")
    public Object grantRole(@RequestBody SysUserRole sysUserRole) throws CustomException {
        return ResponseUtil.success(sysService.grantRole(sysUserRole));
    }
    @ApiOperation(value="赋予角色资源权限",notes = "传入角色id，资源id",response = Boolean.class)
    @PostMapping("/grantPermissions")
    public Object grantPermissions(@RequestBody SysRolePermission sysRolePermission) throws CustomException{
        return ResponseUtil.success(sysService.grantPermission(sysRolePermission));
    }

    @ApiOperation(value="回收用户角色",response = Boolean.class)
    @PostMapping("/revokeRole")
    public Object revokeRole(@RequestBody SysUserRole sysUserRole){
        return ResponseUtil.success(iSysUserRoleService.removeById(sysUserRole.getId()));
    }
    @ApiOperation(value="回收角色资源权限",response = Boolean.class)
    @PostMapping("/revokePermissions")
    public Object revokePermissions(@RequestBody SysRolePermission sysRolePermission){
        return ResponseUtil.success(iSysRolePermissionService.removeById(sysRolePermission.getId()));
    }

}
