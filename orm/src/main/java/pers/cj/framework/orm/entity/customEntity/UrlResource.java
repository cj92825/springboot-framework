package pers.cj.framework.orm.entity.customEntity;

import lombok.Data;

/**
 * @Description 权限url角色关联数据
 * @Author chenj
 * @Date 2019/6/13 9:01
 **/
@Data
public class UrlResource {
    private String url;
    private String roleName;
}
