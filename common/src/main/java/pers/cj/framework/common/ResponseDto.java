package pers.cj.framework.common;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;
import lombok.experimental.Accessors;

import java.util.Date;

/**
 * @Description 统一封装返回结果
 * @Author chenj
 * @Date 2019/5/31 14:55
 **/
@Data
@Accessors(chain = true)
public class ResponseDto {
    private Object data;
    private Integer code=200;
    private String msg;
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Date time=new Date();
}
