package pers.cj.framework.web.sys;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import pers.cj.framework.common.ResponseUtil;
import pers.cj.framework.orm.entity.SysPermission;
import pers.cj.framework.orm.entity.SysPermissionGroup;
import pers.cj.framework.orm.service.ISysPermissionGroupService;
import pers.cj.framework.orm.service.ISysPermissionService;

/**
 * @Description 权限相关控制器
 * @Author chenj
 * @Date 2019/7/3 14:42
 **/
@Api(value = "权限管理相关接口")
@RestController
@RequestMapping("/sys")
public class PermissionController {
    @Autowired
    ISysPermissionService iSysPermissionService;
    @Autowired
    ISysPermissionGroupService iSysPermissionGroupService;

    @ApiOperation(value="查询资源列表分页返回",notes = "传入当前页，每页数量",response = IPage.class)
    @GetMapping("/queryPermissions")
    public Object queryPermissions(@RequestParam("currentPage") long currentPage,
                                   @RequestParam("size") long size){
        return ResponseUtil.success(
                iSysPermissionService.page(
                        new Page<>(currentPage,size)
                ));
    }

    @ApiOperation(value="编辑权限信息",notes = "更新或新增",response = Boolean.class)
    @PostMapping("/editPermission")
    public Object editPermission(@RequestBody SysPermission permission){
        return ResponseUtil.success(iSysPermissionService.saveOrUpdate(permission));
    }

    @ApiOperation(value="删除权限信息",notes = "更新或新增",response = Boolean.class)
    @PostMapping("/deletePermission")
    public Object deletePermission(@RequestBody Long id){
        return ResponseUtil.success(iSysPermissionService.removeById(id));
    }



    @ApiOperation(value="查询资源分组列表",response = SysPermissionGroup.class)
    @GetMapping("/queryPermissionsGroup")
    public Object queryPermissionsGroup(){
        return ResponseUtil.success(iSysPermissionGroupService.list());
    }

}
