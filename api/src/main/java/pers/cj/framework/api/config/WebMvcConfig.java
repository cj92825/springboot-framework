package pers.cj.framework.api.config;


import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.MediaType;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;
import org.springframework.web.filter.CorsFilter;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;


/**
 * @Description mvc配置类，配置序列话方法
 * @Author chenj
 * @Date 2019/5/31 15:24
 **/
@Configuration
public class WebMvcConfig implements WebMvcConfigurer {


//    @Override
//    public void addCorsMappings(CorsRegistry registry) {
//        //设置允许跨域的路径
//        registry.addMapping("/**")
//                //设置允许跨域请求的域名
//                .allowedOrigins("*")
//                .allowedMethods("*")
//                //是否允许证书 不再默认开启
//                .allowCredentials(true)
//                //设置允许的方法
//                .allowedMethods("*")
//                //跨域允许时间
//                .maxAge(3600);
//    }

    /**
     * 这里只修改了日期格式直接在配置文件添加
     * spring:
     *   jackson:
     *     date-format: yyyy-MM-dd HH:mm:ss
     */
//    @Bean
//    public MappingJackson2HttpMessageConverter getConverter() {
//        MappingJackson2HttpMessageConverter jsonConverter = new MappingJackson2HttpMessageConverter();
//        //设置解析JSON工具类
//        ObjectMapper objectMapper = new ObjectMapper();
//        //设置解析日期的工具类
//        objectMapper.setDateFormat(new SimpleDateFormat("yyyy-MM-dd HH:mm:ss"));
//        //忽略未知属性 防止解析报错
//        objectMapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
//        jsonConverter.setObjectMapper(objectMapper);
//        List<MediaType> list = new ArrayList<>();
//        list.add(MediaType.APPLICATION_JSON_UTF8);
//        jsonConverter.setSupportedMediaTypes(list);
//        return jsonConverter;
//    }
}
