package pers.cj.framework.web.sys;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import pers.cj.framework.common.ResponseUtil;
import pers.cj.framework.common.exception.CustomException;
import pers.cj.framework.orm.entity.SysMenu;
import pers.cj.framework.orm.entity.SysUser;
import pers.cj.framework.orm.service.ISysMenuService;

import java.util.List;

/**
 * @Description 菜单管理相关接口
 * @Author chenj
 * @Date 2019/7/2 10:38
 **/
@Api(value = "菜单管理相关接口")
@RestController
@RequestMapping("/sys")
public class MenuController {

    @Autowired
    ISysMenuService iSysMenuService;

    @ApiOperation(value="获取菜单列表",response = SysMenu.class)
    @GetMapping("/menus")
    public Object getMenus()  {
        return ResponseUtil.success(iSysMenuService.list());
    }

    @ApiOperation(value="更新或新增菜单",response = Boolean.class)
    @PostMapping("/editMenu")
    public Object editMenu(@RequestBody SysMenu menu)  {
        return ResponseUtil.success(iSysMenuService.saveOrUpdate(menu));
    }

    @ApiOperation(value="删除菜单",response = Boolean.class)
    @PostMapping("/deleteMenu")
    public Object deleteMenu(@RequestBody List<Long> ids)  {
        return ResponseUtil.success(iSysMenuService.removeByIds(ids));
    }

}
