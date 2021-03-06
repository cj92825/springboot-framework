package pers.cj.framework.common;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import pers.cj.framework.common.constant.ExceptionCode;
import pers.cj.framework.common.exception.CustomException;
import pers.cj.framework.common.model.ResponseDto;

/**
 * @Description 返回结果处理类
 * @Author chenj
 * @Date 2019/5/31 14:39
 **/

public class ResponseUtil {


    public static ResponseEntity<Object> success() {
        return ResponseEntity.ok(new ResponseDto());
    }

    public static ResponseEntity<Object> success(Object object) {
        return ResponseEntity.ok(new ResponseDto().setData(object));
    }


    public static ResponseEntity<Object> fail(){
        return fail("请求异常");
    }
    public static ResponseEntity<Object> fail(String msg) {
        return fail(HttpStatus.INTERNAL_SERVER_ERROR,msg);
    }
    public static ResponseEntity<Object> fail(CustomException exception){
        return fail(exception.getCode(),exception.getMsg());
    }


    public static ResponseEntity<Object> fail(HttpStatus status,String msg) {
        return ResponseEntity.status(status).body(new ResponseDto()
                .setStatus(status.value())
                .setError(status.getReasonPhrase())
                .setMessage(msg));

    }
    public static ResponseEntity<Object> fail(int status,String msg) {
        return ResponseEntity.status(status).body(new ResponseDto().setStatus(status).setMessage(msg));
    }
}
