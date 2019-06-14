package pers.cj.framework.common.exception;

import pers.cj.framework.common.constant.ExceptionCode;

/**
 * @Description 自定义的异常
 * @Author chenj
 * @Date 2019/6/13 14:27
 **/
public class CustomException extends Exception{
    private int code;
    private String msg;

    public CustomException(int code,String msg){
        super(msg);
        this.code=code;
        this.msg=msg;
    }
    public CustomException(ExceptionCode exceptionCode){
        super(exceptionCode.msg());
        this.code=exceptionCode.code();
        this.msg=exceptionCode.msg();
    }

    public int getCode() {
        return code;
    }

    public String getMsg() {
        return msg;
    }
}
