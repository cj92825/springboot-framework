package pers.cj.framework.orm.config;

import com.baomidou.mybatisplus.core.handlers.MetaObjectHandler;
import org.apache.ibatis.reflection.MetaObject;

import java.util.Date;

/**
 * @Description 公共字段自动填充
 * 若需要其他自定义操作，可自行写类继承MetaObjectHandler然后注入,@Order值需要小于Integer.MAX_VALUE
 * @Author chenj
 * @Date 2019/5/30 15:38
 **/
public class MyMetaObjectHandler implements MetaObjectHandler {
    private static final String CREATETIME="createTime";
    private static final String UPDATETIME="updateTime";
    private static final String CREATEBY="createBy";
    private static final String UPDATEBY="updateBy";
    @Override
    public void insertFill(MetaObject metaObject) {
        if(getFieldValByName(CREATETIME,metaObject)==null){
            setFieldValByName(CREATETIME,new Date(),metaObject);
        }
        if(getFieldValByName(CREATEBY,metaObject)==null){
            //后续接入获取用户id接口
            String user=getUserId();
            setFieldValByName(CREATEBY,user,metaObject);
        }
    }



    @Override
    public void updateFill(MetaObject metaObject) {
        if(getFieldValByName(UPDATETIME,metaObject)==null){
            setFieldValByName(UPDATETIME,new Date(),metaObject);
        }
        if(getFieldValByName(UPDATEBY,metaObject)==null){
            String user=getUserId();
            setFieldValByName(UPDATEBY,user,metaObject);
        }
    }

    /**
     * 获取用户id
     * @return
     */
    private String getUserId() {
        return "cj";
    }
}
