package pers.cj.framework.api.service.sys;

import com.baomidou.mybatisplus.core.metadata.IPage;
import pers.cj.framework.orm.entity.SysPermission;
import pers.cj.framework.orm.entity.SysRole;
import pers.cj.framework.orm.entity.SysUser;

/**
 * @Description 系统管理服务接口
 * @Author chenj
 * @Date 2019/6/12 11:23
 **/
public interface SysService {

    /**
     * 新增用户
     * @param sysUser
     * @return
     */
    Long addUser(SysUser sysUser);


}
