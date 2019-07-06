package pers.cj.framework.orm.service;

import pers.cj.framework.orm.entity.SysUserRole;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.List;

/**
 * <p>
 * 用户角色关联表 服务类
 * </p>
 *
 * @author chenj
 * @since 2019-06-04
 */
public interface ISysUserRoleService extends IService<SysUserRole> {


    /**
     * 通过userID查询角色列表
     * @param userId
     * @return
     */
    List<SysUserRole> listByUserId(long userId);
}
