package pers.cj.framework.orm.service.impl;

import pers.cj.framework.orm.entity.SysMenu;
import pers.cj.framework.orm.mapper.SysMenuMapper;
import pers.cj.framework.orm.service.ISysMenuService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

/**
 * <p>
 * 菜单管理 服务实现类
 * </p>
 *
 * @author chenj
 * @since 2019-07-08
 */
@Service
public class SysMenuServiceImpl extends ServiceImpl<SysMenuMapper, SysMenu> implements ISysMenuService {

}
