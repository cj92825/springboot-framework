package pers.cj.framework.api.service.sys;

import com.baomidou.mybatisplus.core.metadata.IPage;
import pers.cj.framework.common.exception.CustomException;
import pers.cj.framework.orm.entity.*;

import java.util.List;

/**
 * @Description 系统管理服务接口
 * @Author chenj
 * @Date 2019/6/12 11:23
 **/
public interface SysService {

    /**
     * 新增或修改用户信息
     * @param sysUser
     * @return
     * @throws CustomException
     */
    Long editUser(SysUser sysUser) throws CustomException;

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
     * 新增或更新角色
     * @param sysRole
     * @return 角色主键id
     * @throws CustomException
     */
    Long editRole(SysRole sysRole) throws  CustomException;

    /**
     * 批量授予角色权限
     * @param roleId
     * @param deletePermissionIds
     * @param addPermissionIds
     * @return
     */
    boolean grantPermissions(long roleId, List<Long> deletePermissionIds,List<Long> addPermissionIds);
}
