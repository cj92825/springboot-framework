package pers.cj.framework.orm.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import org.apache.ibatis.reflection.wrapper.BaseWrapper;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.cache.annotation.EnableCaching;
import pers.cj.framework.orm.entity.SysPermission;
import pers.cj.framework.orm.entity.SysRole;
import pers.cj.framework.orm.entity.SysUser;
import pers.cj.framework.orm.mapper.SysUserMapper;
import pers.cj.framework.orm.service.ISysUserService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

import java.io.Serializable;
import java.sql.Wrapper;

/**
 * <p>
 * 用户管理 服务实现类
 * </p>
 *
 * @author chenj
 * @since 2019-05-31
 */
@Service
public class SysUserServiceImpl extends ServiceImpl<SysUserMapper, SysUser> implements ISysUserService {

    @Cacheable(value = "sysUser",key = "#id")
    @Override
    public SysUser getById(Serializable id) {
        return super.getById(id);
    }

    @CacheEvict(value = "sysUser",key = "#entity.id")
    @Override
    public boolean updateById(SysUser entity) {
        return super.updateById(entity);
    }


    @Override
    public SysUser getByAccount(String account) {
        return getOne(new QueryWrapper<SysUser>().eq("account",account));
    }




}
