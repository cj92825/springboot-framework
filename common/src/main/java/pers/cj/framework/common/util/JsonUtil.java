package pers.cj.framework.common.util;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

/**
 * @Description jackson的工具类
 * @Author chenj
 * @Date 2019/6/10 17:13
 **/
public class JsonUtil {
    private static ObjectMapper objectMapper=new ObjectMapper();

    public static String toJson(Object data)throws JsonProcessingException {
        return objectMapper.writeValueAsString(data);
    }
}
