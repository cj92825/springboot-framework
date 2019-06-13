package pers.cj.framework.common.security.util;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

/**
 * @Description BCrypt加密工具类
 * @Author chenj
 * @Date 2019/6/4 17:10
 **/
public class BCryptPasswordEncoderUtil {
    public static String encode(String passWord){
        BCryptPasswordEncoder encoder=new BCryptPasswordEncoder();
        return encoder.encode(passWord);
    }

    public static void main(String[] args) {
        //需要加密的密码
        String passWord="123456";
        System.out.println("密文密码为"+encode(passWord));
    }
}
