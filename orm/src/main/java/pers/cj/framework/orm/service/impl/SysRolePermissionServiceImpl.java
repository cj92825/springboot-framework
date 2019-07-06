package pers.cj.framework.orm.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import org.springframework.cache.annotation.CacheEvict;
import pers.cj.framework.orm.entity.SysRolePermission;
import pers.cj.framework.orm.mapper.SysRolePermissionMapper;
import pers.cj.framework.orm.service.ISysRolePermissionService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

import java.io.Serializable;
import java.util.List;

/**
 * <p>
 * 角色权限关联表 服务实现类
 * </p>
 *
 * @author chenj
 * @since 2019-06-11
 */
@Service
public class SysRolePermissionServiceImpl extends ServiceImpl<SysRolePermissionMapper, SysRolePermission> implements ISysRolePermissionService {

    @CacheEvict(value = "UrlResource",key="'getRoleResource'")
    @Override
    public boolean save(SysRolePermission entity) {
        return super.save(entity);
    }
    @CacheEvict(value = "UrlResource",key=" 'getRoleResource'")
    @Override
    public boolean removeById(Serializable id) {
        return super.removeById(id);
    }


    @Override
    public List<SysRolePermission> listByRoleId(Long roleId) {
        return list(new QueryWrapper<SysRolePermission>().eq("role_id",roleId));
    }
}
