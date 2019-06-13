package pers.cj.framework.orm.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import pers.cj.framework.orm.entity.SysPermission;
import pers.cj.framework.orm.mapper.SysPermissionMapper;
import pers.cj.framework.orm.service.ISysPermissionService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * <p>
 * 权限管理 服务实现类
 * </p>
 *
 * @author chenj
 * @since 2019-06-10
 */
@Service
public class SysPermissionServiceImpl extends ServiceImpl<SysPermissionMapper, SysPermission> implements ISysPermissionService {

    @Override
    public List<SysPermission> listByRoleId(int roleId) {
        return list(new QueryWrapper<SysPermission>().eq("role_id",roleId));
    }

    @Override
    public List<SysPermission> listByRoleName(String roleName) {
        return list(new QueryWrapper<SysPermission>().eq("name ",roleName));
    }
}
