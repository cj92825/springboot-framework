package pers.cj.framework.api.service.sys.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.security.core.session.SessionInformation;
import org.springframework.security.core.session.SessionRegistry;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import pers.cj.framework.api.service.sys.SysService;
import pers.cj.framework.common.ResponseUtil;
import pers.cj.framework.common.constant.ExceptionCode;
import pers.cj.framework.common.exception.CustomException;
import pers.cj.framework.common.security.model.CustomUser;
import pers.cj.framework.common.security.util.BCryptPasswordEncoderUtil;
import pers.cj.framework.orm.entity.*;
import pers.cj.framework.orm.service.*;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

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
    ISysRolePermissionService iSysRolePermissionService;
    @Autowired
    ISysUserRoleService iSysUserRoleService;
    @Autowired
    SessionRegistry sessionRegistry;

    @Override
    public Long editUser( SysUser sysUser) throws CustomException {
        if(StringUtils.isEmpty(sysUser.getAccount())){
            throw new CustomException(ExceptionCode.ACCOUNT_EMPTY);
        }
        //查询account是否已经存在
        int count=iSysUserService.count(new QueryWrapper<SysUser>()
                .ne(sysUser.getId()!=null,"id",sysUser.getId())
                .eq("account",sysUser.getAccount()));
        if (count>0) {
            throw new CustomException(ExceptionCode.ACCOUNT_EXIST);
        }
        if(sysUser.getPassword()!=null) {
            //加密密码
            sysUser.setPassword(BCryptPasswordEncoderUtil.encode(sysUser.getPassword()));
            if(sysUser.getId()!=null) {
                //如果修改了密码，踢掉用户
                List<Object> users = sessionRegistry.getAllPrincipals();
                for (Object principal : users) {
                    if (principal instanceof CustomUser) {
                        String principalName = ((CustomUser)principal).getUsername();
                        if (principalName.equals(sysUser.getAccount())) {
                            // 参数二：是否包含过期的Session
                            List<SessionInformation> sessionsInfo = sessionRegistry.getAllSessions(principal, false);
                            if (null != sessionsInfo && sessionsInfo.size() > 0) {
                                for (SessionInformation sessionInformation : sessionsInfo) {
                                    sessionInformation.expireNow();
                                }
                            }
                        }
                    }
                }
            }
        }
        iSysUserService.saveOrUpdate(sysUser);
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
    public Long editRole(SysRole sysRole) throws CustomException {
        int count=iSysRoleService.count(new QueryWrapper<SysRole>()
                .ne(sysRole.getId()!=null,"id",sysRole.getId())
                .eq("name",sysRole.getName()));
        if(count>0){
            throw new CustomException(ExceptionCode.ROLE_EXIST);
        }
        iSysRoleService.saveOrUpdate(sysRole);
        return sysRole.getId();
    }

    @CacheEvict(value = "UrlResource",key="'getRoleResource'")
    @Transactional(rollbackFor = Exception.class)
    @Override
    public boolean grantPermissions(long roleId, List<Long> deletePermissionIds,List<Long> addPermissionIds) {
        if(deletePermissionIds.size()>0){
            iSysRolePermissionService.removeByIds(deletePermissionIds);
        }
        if(addPermissionIds.size()>0){
            List<SysRolePermission> rolePermissions=new ArrayList<>();
            for(long permission : addPermissionIds) {
                rolePermissions.add(new SysRolePermission().setRoleId(roleId).setPermissionId(permission));
            }
            iSysRolePermissionService.saveBatch(rolePermissions);
        }
        return true;
    }


}
