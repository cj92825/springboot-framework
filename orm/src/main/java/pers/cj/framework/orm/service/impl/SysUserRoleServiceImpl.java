package pers.cj.framework.orm.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import pers.cj.framework.orm.entity.SysUserRole;
import pers.cj.framework.orm.mapper.SysUserRoleMapper;
import pers.cj.framework.orm.service.ISysUserRoleService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * <p>
 * 用户角色关联表 服务实现类
 * </p>
 *
 * @author chenj
 * @since 2019-06-04
 */
@Service
public class SysUserRoleServiceImpl extends ServiceImpl<SysUserRoleMapper, SysUserRole> implements ISysUserRoleService {


    @Override
    public List<SysUserRole> listByUserId(long id) {
        return list(Wrappers.<SysUserRole>lambdaQuery().eq(SysUserRole::getUserId,id));
    }


}
