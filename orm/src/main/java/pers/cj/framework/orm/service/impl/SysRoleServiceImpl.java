package pers.cj.framework.orm.service.impl;

import org.springframework.cache.annotation.Cacheable;
import pers.cj.framework.orm.entity.SysRole;
import pers.cj.framework.orm.entity.customentity.UrlResource;
import pers.cj.framework.orm.mapper.SysRoleMapper;
import pers.cj.framework.orm.service.ISysRoleService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * <p>
 * 角色管理 服务实现类
 * </p>
 *
 * @author chenj
 * @since 2019-06-04
 */
@Service
public class SysRoleServiceImpl extends ServiceImpl<SysRoleMapper, SysRole> implements ISysRoleService {

    /**
     * 缓存权限角色关联列表
     * @return
     */
    @Cacheable(value = "UrlResource",key="'getRoleResource'" )
    @Override
    public List<UrlResource> getRoleByUrl() {
        return baseMapper.getRoleByUrl();
    }

}
