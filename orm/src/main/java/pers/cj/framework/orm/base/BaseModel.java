package pers.cj.framework.orm.base;

import com.baomidou.mybatisplus.annotation.FieldFill;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableLogic;
import lombok.Data;
import lombok.experimental.Accessors;

import java.io.Serializable;
import java.util.Date;

/**
 * @Description 公共字段类
 * @Author chenj
 * @Date 2019/5/29 17:15
 **/
@Data
@Accessors(chain = true)
public abstract class BaseModel implements Serializable {
    private static final long serialVersionUID = 1L;

    /**
     * 创建时间
     */
    @TableField(fill = FieldFill.INSERT)
    Date createTime;

    /**
     * 创建人id
     */
    @TableField(fill = FieldFill.INSERT)
    String createBy;

    /**
     * 更新时间
     */
    @TableField(fill = FieldFill.UPDATE)
    Date updateTime;

    /**
     * 更新人id
     */
    @TableField(fill = FieldFill.UPDATE)
    String updateBy;

    /**
     * 逻辑删除标记
     */
    @TableLogic
    Integer deleted;
}
