package pers.cj.framework.common.constant;

/**
 * @Description 返回码
 * @Author chenj
 * @Date 2019/6/13 14:15
 **/
public enum ExceptionCode {
    /**
     *
     */
    OK(200, "OK"),
    ACCOUNT_EMPTY(4001,"account为空"),
    ACCOUNT_EXIST(4002,"account已经存在"),
    ROLE_EXIST(4003,"角色已经存在"),
    USER_HAD_ROLE(4004,"用户已有该角色"),
    ROLE_HAD_PERMISSION(4005,"角色已有该权限"),
    LOGIN_FAIL(4006,"登录失败"),
    NOT_FIND_FILE(4100,"没有发现文件"),
    UPLOAD_ERROR(4101,"文件上传异常");
    private final int code;

    private final String msg;
    ExceptionCode(int code, String msg){
        this.code=code;
        this.msg=msg;
    }

    /**
     *
     * @return
     */
    public int code() {
        return this.code;
    }

    /**
     *
     * @return
     */
    public String msg() {
        return this.msg;
    }
}
