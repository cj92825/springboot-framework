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
 * 菜单管理
 * </p>
 *
 * @author chenj
 * @since 2019-07-02
 */
@Data
@EqualsAndHashCode(callSuper = true)
@JsonInclude(JsonInclude.Include.NON_NULL)
@Accessors(chain = true)
public class SysMenu extends BaseModel {

    private static final long serialVersionUID = 1L;

    /**
     * 主键ID
     */
    @TableId(value = "id", type = IdType.AUTO)
    private Long id;

    /**
     * url路径
     */
    private String path;

    /**
     * 组件
     */
    private String component;

    private String name;

    private String redirect;

    private String meta;

    private Long parentId;


}
