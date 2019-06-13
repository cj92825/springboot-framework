package pers.cj.framework.orm.service;

import pers.cj.framework.orm.entity.SysPermission;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.List;

/**
 * <p>
 * 权限管理 服务类
 * </p>
 *
 * @author chenj
 * @since 2019-06-10
 */
public interface ISysPermissionService extends IService<SysPermission> {
    /**
     * 通过角色id查询权限信息
     * @param roleId
     * @return
     */
    List<SysPermission> listByRoleId(int roleId);
    /**
     * 通过角色名查询权限信息
     * @param roleName
     * @return
     */
    List<SysPermission> listByRoleName(String roleName);
}
