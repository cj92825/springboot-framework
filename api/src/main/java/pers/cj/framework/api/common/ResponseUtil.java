package pers.cj.framework.api.common;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;
import lombok.experimental.Accessors;
import org.springframework.http.ResponseEntity;

import java.util.Date;

/**
 * @Description TODO
 * @Author chenj
 * @Date 2019/5/31 14:39
 **/

public class ResponseUtil {

    public static ResponseEntity<?> success(Object object) {
        return ResponseEntity.ok(new ResponseDto().setData(object));
    }
    public static ResponseEntity<?> success() {
        return ResponseEntity.ok(new ResponseDto());
    }

}
