package pers.cj.framework.orm.service;

import pers.cj.framework.orm.entity.customentity.UrlResource;
import pers.cj.framework.orm.entity.SysRole;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.List;

/**
 * <p>
 * 角色管理 服务类
 * </p>
 *
 * @author chenj
 * @since 2019-06-04
 */
public interface ISysRoleService extends IService<SysRole> {

    /**
     * 通过url获取角色名列表
     * @return
     */
    List<UrlResource>  getRoleByUrl();
}
