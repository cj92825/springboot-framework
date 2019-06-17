package pers.cj.framework.common.upload.config;

import lombok.Data;

/**
 * @Description AliOss配置
 * @Author chenj
 * @Date 2019/6/15 11:01
 **/
@Data
public class AliOos {

    private boolean enable = false;
    private String endpoint;
    private String accessKeyId;
    private String accessKeySecret;
    private String bucketName;
    private String path;
}
