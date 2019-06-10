package pers.cj.framework.common;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

/**
 * @Description 返回结果处理类
 * @Author chenj
 * @Date 2019/5/31 14:39
 **/

public class ResponseUtil {

    public static ResponseEntity<Object> success() {
        return success(new ResponseDto());
    }

    public static ResponseEntity<Object> success(Object object) {
        return ResponseEntity.ok(new ResponseDto().setData(object));
    }


    public static ResponseEntity<Object> fail() {
        return fail("请求异常");
    }
    public static ResponseEntity<Object> fail(String msg) {
        return fail(HttpStatus.INTERNAL_SERVER_ERROR.value(),msg);
    }
    public static ResponseEntity<Object> fail(Integer code,String msg) {
        return ResponseEntity.status(code).body(new ResponseDto().setCode(code).setMsg(msg));
    }
}
