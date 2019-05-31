package pers.cj.framework.web;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import io.swagger.annotations.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import pers.cj.framework.api.common.ResponseUtil;
import pers.cj.framework.orm.entity.SysUser;
import pers.cj.framework.orm.service.ISysUserService;

/**
 * @Description Controller测试类
 * @Author chenj
 * @Date 2019/5/28 17:56
 **/
@Api(tags = "Api测试类",value="Api测试类" )
@RestController
//@MapperScan("pers.cj.framework.*.mapper")//指定mapper包路径
public class TestController {
    @Autowired
    ISysUserService iSysUserService;

    @Value("${mybatis.logic}")
    Boolean value;

    @GetMapping("/")
    public String test(){
        return "显示中文";
    }



    @GetMapping("/query")
    public Object testMybatis(){
        return iSysUserService.list();
    }
    @GetMapping("/delete")
    public Object testLogicDelete(){
        return iSysUserService.removeById(1L);
    }

    @ApiOperation(value = "分页查询用户")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "currentPage", value = "当前页码", required = true, dataType = "Int",paramType = "query")
    })
    @GetMapping("/pageTest")
    public Object testLogicDelete(Integer currentPage){
        SysUser sysUser=new SysUser();
        Page<SysUser> page=new Page<>(currentPage,1);
        return iSysUserService.page(page);
    }

    @GetMapping("/insert")
    public Object insert(){
        SysUser sysUser=new SysUser().setAccount("aaa");

        return iSysUserService.save(sysUser);
    }
    @GetMapping("/test")
    public Object test1(){
        return value;
    }

    @GetMapping("/dto")
    public Object dto(){
        SysUser sysUser=iSysUserService.getById(5L);
//        return sysUser;
        return ResponseUtil.success(sysUser);
    }
}
