package pers.cj.framework.api.service.sys.impl;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pers.cj.framework.api.service.sys.SysService;
import pers.cj.framework.common.security.util.BCryptPasswordEncoderUtil;
import pers.cj.framework.orm.entity.SysPermission;
import pers.cj.framework.orm.entity.SysRole;
import pers.cj.framework.orm.entity.SysUser;
import pers.cj.framework.orm.service.ISysPermissionService;
import pers.cj.framework.orm.service.ISysRoleService;
import pers.cj.framework.orm.service.ISysUserService;

/**
 * @Description 系统管理实现类
 * @Author chenj
 * @Date 2019/6/12 11:24
 **/
@Service
public class SysServiceImpl implements SysService {

    @Autowired
    ISysUserService iSysUserService;
    @Autowired
    ISysRoleService iSysRoleService;
    @Autowired
    ISysPermissionService iSysPermissionService;


    @Override
    public Long addUser( SysUser sysUser) {
        //加密密码
        sysUser.setPassword(BCryptPasswordEncoderUtil.encode(sysUser.getPassword()));
        iSysUserService.save(sysUser);
        return sysUser.getId();
    }




}
