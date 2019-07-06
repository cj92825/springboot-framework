package pers.cj.framework.web.sys;

import com.alibaba.fastjson.JSONObject;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.core.toolkit.StringUtils;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.apache.commons.collections4.ListUtils;
import org.apache.commons.collections4.MapUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.CollectionUtils;
import org.springframework.web.bind.annotation.*;
import pers.cj.framework.api.service.sys.SysService;
import pers.cj.framework.common.ResponseUtil;
import pers.cj.framework.common.exception.CustomException;
import pers.cj.framework.orm.entity.*;
import pers.cj.framework.orm.service.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

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
    ISysUserRoleService iSysUserRoleService;

    @Autowired
    ISysRolePermissionService iSysRolePermissionService;

    @ApiOperation(value="查询用户拥有的角色",response = SysUserRole.class)
    @GetMapping("/getRolesByUserId")
    public Object getRolesByUserId(@RequestParam("userId") long userId){
        return ResponseUtil.success(iSysUserRoleService.listByUserId(userId));
    }

    @ApiOperation(value="赋予用户角色",notes = "传入用户id，角色id",response = Boolean.class)
    @PostMapping("/grantRole")
    public Object grantRole(@RequestBody SysUserRole sysUserRole) throws CustomException {
        return ResponseUtil.success(sysService.grantRole(sysUserRole));
    }
    @ApiOperation(value="回收用户角色",response = Boolean.class)
    @PostMapping("/revokeRole")
    public Object revokeRole(@RequestBody SysUserRole sysUserRole){
        return ResponseUtil.success(iSysUserRoleService.removeById(sysUserRole.getId()));
    }

    @ApiOperation(value="查询角色拥有的权限",response = SysRolePermission.class)
    @GetMapping("/getPermissionByRoleId")
    public Object getPermissionByRoleId(@RequestParam("roleId") long roleId){
        return ResponseUtil.success(iSysRolePermissionService.listByRoleId(roleId));
    }

    @ApiOperation(value="回收角色资源权限",response = Boolean.class)
    @PostMapping("/revokePermission")
    public Object revokePermission(@RequestBody SysRolePermission sysRolePermission){
        return ResponseUtil.success(iSysRolePermissionService.removeById(sysRolePermission.getId()));
    }
    @ApiOperation(value="赋予角色资源权限",notes = "传入角色id，资源id",response = Boolean.class)
    @PostMapping("/grantPermission")
    public Object grantPermission(@RequestBody SysRolePermission sysRolePermission) throws CustomException{
        return ResponseUtil.success(sysService.grantPermission(sysRolePermission));
    }



    @ApiOperation(value="批量赋予角色资源权限",notes = "传入角色id，资源id",response = Boolean.class)
    @PostMapping("/grantPermissions")
    public Object grantPermissions(@RequestBody Map<String,Object> paramMap){
        long roleId= MapUtils.getLongValue(paramMap,"roleId");
        List<Long> addPermissionIds= ((List<Integer>) paramMap.get("addPermissionIds")).stream().map(s->s.longValue()).collect(Collectors.toList());
        List<Long> deletePermissionIds= ((List<Integer>) paramMap.get("deletePermissionIds")).stream().map(s->s.longValue()).collect(Collectors.toList());;
        return ResponseUtil.success(sysService.grantPermissions(roleId,deletePermissionIds,addPermissionIds));
    }




}
