package pers.cj.framework.orm.service.impl;

import pers.cj.framework.orm.entity.SysUser;
import pers.cj.framework.orm.mapper.SysUserMapper;
import pers.cj.framework.orm.service.ISysUserService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

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

}
