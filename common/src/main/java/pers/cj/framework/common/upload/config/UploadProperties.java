package pers.cj.framework.common.upload.config;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.context.properties.NestedConfigurationProperty;
import org.springframework.stereotype.Component;

/**
 * @Description FileSystem配置
 * @Author chenj
 * @Date 2019/6/15 9:12
 **/
@Component
@ConfigurationProperties(prefix = "fs")
@Data
public class UploadProperties {
    /**
     * 文件存储目录
     */
    private String filePath="/tmp";
    /**
     * 阿里云OSS配置
     */
    @NestedConfigurationProperty
    private AliOos aliOos;
}
