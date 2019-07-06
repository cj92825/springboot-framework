package pers.cj.framework.web.sys;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import pers.cj.framework.api.service.sys.SysService;
import pers.cj.framework.common.ResponseUtil;
import pers.cj.framework.common.exception.CustomException;
import pers.cj.framework.orm.entity.SysRole;
import pers.cj.framework.orm.entity.SysRolePermission;
import pers.cj.framework.orm.entity.SysUserRole;
import pers.cj.framework.orm.service.ISysRolePermissionService;
import pers.cj.framework.orm.service.ISysRoleService;

import java.util.ArrayList;
import java.util.List;

/**
 * @Description 角色管理相关接口
 * @Author chenj
 * @Date 2019/7/3 17:05
 **/
@Api(value = "角色管理相关接口")
@RestController
@RequestMapping("/sys")
public class RoleController {
    @Autowired
    ISysRoleService iSysRoleService;

    @Autowired
    SysService sysService;



    @ApiOperation(value="查询角色列表分页返回",notes = "传入当前页，每页数量",response = IPage.class)
    @GetMapping("/roles")
    public Object queryRoles(@RequestParam("currentPage") long currentPage,
                             @RequestParam("size") long size){
        return ResponseUtil.success(
                iSysRoleService.page(
                        new Page<>(currentPage,size)
                ));
    }
    @ApiOperation(value="新增或更新角色",response = Integer.class)
    @ApiImplicitParam(name = "sysRole", value = "用户信息", required = true, dataType = "sysRole",paramType = "body")
    @PostMapping("/editRole")
    public Object editRole(@RequestBody SysRole sysRole) throws CustomException {
        return ResponseUtil.success(sysService.editRole(sysRole));
    }

    @ApiOperation(value="删除角色",response = Boolean.class)
    @PostMapping("/deleteRole")
    public Object deleteRole(@RequestBody Long id){
        return ResponseUtil.success(iSysRoleService.removeById(id));
    }



}
