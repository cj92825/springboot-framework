package pers.cj.framework.orm.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import pers.cj.framework.orm.entity.SysPermission;
import pers.cj.framework.orm.entity.SysRole;
import pers.cj.framework.orm.entity.SysUser;
import com.baomidou.mybatisplus.extension.service.IService;

/**
 * <p>
 * 用户管理 服务类
 * </p>
 *
 * @author chenj
 * @since 2019-05-31
 */
public interface ISysUserService extends IService<SysUser> {

    /**
     * 通过用户名获取用户信息
     * @param account
     * @return
     */
    SysUser getByAccount(String account);



}
