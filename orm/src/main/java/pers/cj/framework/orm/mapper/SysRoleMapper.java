package pers.cj.framework.orm.mapper;

import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.ResultType;
import org.apache.ibatis.annotations.Select;
import pers.cj.framework.orm.entity.SysRole;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import pers.cj.framework.orm.entity.customEntity.UrlResource;

import java.util.List;

/**
 * <p>
 * 角色管理 Mapper 接口
 * </p>
 *
 * @author chenj
 * @since 2019-06-04
 */
public interface SysRoleMapper extends BaseMapper<SysRole> {

    /**
     * 通过url获取角色名列表
     * @return
     */
//    @Select("SELECT a.name FROM sys_role a,sys_role_permission b,sys_permission c where c.url=#{url} and c.id=b.permission_id and a.id=b.role_id")
    @Select("SELECT c.url,a.role_name FROM sys_role a,sys_role_permission b,sys_permission c where c.id=b.permission_id and a.id=b.role_id")
    @ResultType(UrlResource.class)
    List<UrlResource> getRoleByUrl();
//    List<UrlResource> getRoleByUrl(@Param("url") String url);
}
