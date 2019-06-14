package pers.cj.framework.api.service.sys;

import com.baomidou.mybatisplus.core.metadata.IPage;
import pers.cj.framework.common.exception.CustomException;
import pers.cj.framework.orm.entity.*;

/**
 * @Description 系统管理服务接口
 * @Author chenj
 * @Date 2019/6/12 11:23
 **/
public interface SysService {

    /**
     * 新增用户
     * @param sysUser
     * @return
     */
    Long addUser(SysUser sysUser) throws CustomException;

    /**
     * 授予用户角色
     * @param sysUserRole
     * @return
     * @throws CustomException
     */
    boolean grantRole(SysUserRole sysUserRole) throws CustomException;

    /**
     * 授予角色权限
     * @param sysRolePermission
     * @return
     * @throws CustomException
     */
    boolean grantPermission(SysRolePermission sysRolePermission) throws CustomException;

    /**
     * 新增角色
     * @param sysRole
     * @return 角色主键id
     * @throws CustomException
     */
    Long addRole(SysRole sysRole) throws  CustomException;
}
