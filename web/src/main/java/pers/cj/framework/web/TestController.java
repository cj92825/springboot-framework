package pers.cj.framework.web;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @Description Controller测试类
 * @Author chenj
 * @Date 2019/5/28 17:56
 **/
@RestController
public class TestController {

    @GetMapping("/")
    public String test(){
        return "hello";
    }
}
