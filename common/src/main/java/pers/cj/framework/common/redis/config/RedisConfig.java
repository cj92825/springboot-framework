package pers.cj.framework.common.redis.config;

import com.alibaba.fastjson.support.spring.FastJsonRedisSerializer;
import com.alibaba.fastjson.support.spring.GenericFastJsonRedisSerializer;
import org.springframework.cache.CacheManager;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.ResourceLoader;
import org.springframework.data.redis.cache.RedisCacheConfiguration;
import org.springframework.data.redis.cache.RedisCacheManager;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.data.redis.serializer.RedisSerializationContext;

import java.time.Duration;
import java.util.HashSet;
import java.util.Set;


/**
 * @Description redis缓存配置
 * @Author chenj
 * @Date 2019/6/1 11:08
 **/
@Configuration
@EnableCaching
public class RedisConfig {
    @Bean
    CacheManager cacheManager(RedisConnectionFactory connectionFactory ,ResourceLoader resourceLoader) {
        //使用Jackson2JsonRedisSerializer来序列化和反序列化redis的value值
//        GenericJackson2JsonRedisSerializer serializer = new GenericJackson2JsonRedisSerializer();
//        使用Fastjson2JsonRedisSerializer来序列化和反序列化redis的value值
        GenericFastJsonRedisSerializer serializer=new GenericFastJsonRedisSerializer();
        //设置默认过期时间为1小时
        RedisCacheConfiguration config=RedisCacheConfiguration.defaultCacheConfig().entryTtl(Duration.ofHours(1L))
                .serializeValuesWith(RedisSerializationContext.SerializationPair.fromSerializer(serializer))
                .disableCachingNullValues(); //不缓存空值

        // 设置一个初始化的缓存空间set集合
        Set<String> cacheNames = new HashSet<>();
        return RedisCacheManager.builder(connectionFactory)
                .cacheDefaults(config)
                .initialCacheNames(cacheNames)
                .transactionAware()
                .build();
    }
}
