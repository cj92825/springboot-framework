package pers.cj.framework.orm.service;

import pers.cj.framework.orm.entity.SysRolePermission;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.List;

/**
 * <p>
 * 角色权限关联表 服务类
 * </p>
 *
 * @author chenj
 * @since 2019-06-11
 */
public interface ISysRolePermissionService extends IService<SysRolePermission> {
    /**
     * 通过角色id查询权限id列表
     * @param roleId
     * @return
     */
    List<SysRolePermission> listByRoleId(Long roleId);
}
