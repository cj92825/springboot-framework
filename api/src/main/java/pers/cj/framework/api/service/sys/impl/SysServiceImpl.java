package pers.cj.framework.api.service.sys.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import pers.cj.framework.api.service.sys.SysService;
import pers.cj.framework.common.ResponseUtil;
import pers.cj.framework.common.constant.ExceptionCode;
import pers.cj.framework.common.exception.CustomException;
import pers.cj.framework.common.security.util.BCryptPasswordEncoderUtil;
import pers.cj.framework.orm.entity.*;
import pers.cj.framework.orm.service.*;

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
    @Autowired
    ISysRolePermissionService iSysRolePermissionService;
    @Autowired
    ISysUserRoleService iSysUserRoleService;

    @Override
    public Long addUser( SysUser sysUser) throws CustomException {
        if(StringUtils.isEmpty(sysUser.getAccount())){
            throw new CustomException(ExceptionCode.ACCOUNT_EMPTY);
        }
        //查询account是否已经存在
        boolean exist=iSysUserService.exist(sysUser.getAccount());
        if(exist){
            throw new CustomException(ExceptionCode.ACCOUNT_EXIST);
        }
        //加密密码
        sysUser.setPassword(BCryptPasswordEncoderUtil.encode(sysUser.getPassword()));
        iSysUserService.save(sysUser);
        return sysUser.getId();
    }

    @Override
    public boolean grantRole(SysUserRole sysUserRole) throws CustomException {
        int count=iSysUserRoleService.count(new QueryWrapper<SysUserRole>()
                .eq("user_id",sysUserRole.getUserId())
                .eq("role_id",sysUserRole.getRoleId()));
        if(count>0){
            throw new CustomException(ExceptionCode.USER_HAD_ROLE);
        }
        return iSysUserRoleService.save(sysUserRole);
    }

    @Override
    public boolean grantPermission(SysRolePermission sysRolePermission) throws CustomException {
        int count=iSysRolePermissionService.count(new QueryWrapper<SysRolePermission>()
                .eq("role_id",sysRolePermission.getRoleId())
                .eq("permission_id",sysRolePermission.getPermissionId()));
        if(count>0){
            throw new CustomException(ExceptionCode.ROLE_HAD_PERMISSION);
        }
        return iSysRolePermissionService.save(sysRolePermission);
    }

    @Override
    public Long addRole(SysRole sysRole) throws CustomException {
        int count=iSysRoleService.count(new QueryWrapper<SysRole>()
                .eq("name",sysRole.getName()));
        if(count>0){
            throw new CustomException(ExceptionCode.ROLE_EXIST);
        }
        iSysRoleService.save(sysRole);
        return sysRole.getId();
    }


}
