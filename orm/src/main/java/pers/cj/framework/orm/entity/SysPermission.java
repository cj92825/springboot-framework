package pers.cj.framework.orm.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.fasterxml.jackson.annotation.JsonInclude;
import pers.cj.framework.orm.base.BaseModel;
import com.baomidou.mybatisplus.annotation.TableId;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

/**
 * <p>
 * 权限管理
 * </p>
 *
 * @author chenj
 * @since 2019-06-27
 */
@Data
@EqualsAndHashCode(callSuper = true)
@JsonInclude(JsonInclude.Include.NON_NULL)
@Accessors(chain = true)
public class SysPermission extends BaseModel {

    private static final long serialVersionUID = 1L;

    /**
     * 主键ID
     */
    @TableId(value = "id", type = IdType.AUTO)
    private Long id;

    /**
     * url路径
     */
    private String uri;

    /**
     * 权限
     */
    private String permission;

    private String name;

    private String description;

    private Integer groupId;


}
