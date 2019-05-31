package pers.cj.framework.orm;

import com.alibaba.fastjson.JSON;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import pers.cj.framework.orm.entity.SysUser;
import pers.cj.framework.orm.service.ISysUserService;

/**
 * @Description TODO
 * @Author chenj
 * @Date 2019/5/30 16:03
 **/
@RunWith(SpringRunner.class)
@SpringBootTest
public class TestClass {
    private static final Logger LOGGER = LoggerFactory.getLogger(TestClass.class);
    @Autowired
    ISysUserService iSysUserService;

    @Test
    public void testAutoFill(){
        SysUser sysUser=new SysUser().setAccount("aaa");
        iSysUserService.save(sysUser);
        LOGGER.info(JSON.toJSONString(iSysUserService.getById(sysUser.getId())));
    }
}
