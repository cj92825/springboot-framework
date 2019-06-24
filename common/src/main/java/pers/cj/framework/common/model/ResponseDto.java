package pers.cj.framework.common.model;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Data;
import lombok.experimental.Accessors;
import org.springframework.http.HttpStatus;
import org.springframework.util.StringUtils;
import pers.cj.framework.common.constant.ExceptionCode;
import pers.cj.framework.common.util.ServletUtil;

import java.util.Date;

/**
 * @Description 统一封装返回结果
 * @Author chenj
 * @Date 2019/5/31 14:55
 **/
@Data
@JsonInclude(JsonInclude.Include.NON_NULL)
@Accessors(chain = true)
public class ResponseDto {
    private Object data;
    private Integer status= ExceptionCode.OK.code();
    private String message;
    private String error;
    private String path;
    @JsonIgnore
    private HttpStatus httpStatus;

    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Date timestamp=new Date();

    public String getError() {
        return StringUtils.isEmpty(error)&&status!=200?message:error;
    }

    public String getPath() {
        return ServletUtil.getRequestURI();
    }

    public ResponseDto setHttpStatus(HttpStatus httpStatus) {
        this.error = httpStatus.getReasonPhrase();
        this.status=httpStatus.value();
        return this;
    }
}
