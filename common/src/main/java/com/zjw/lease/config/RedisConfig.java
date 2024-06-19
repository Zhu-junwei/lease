package com.zjw.lease.config;

import com.fasterxml.jackson.annotation.JsonAutoDetect;
import com.fasterxml.jackson.annotation.JsonTypeInfo;
import com.fasterxml.jackson.annotation.PropertyAccessor;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.jsontype.impl.LaissezFaireSubTypeValidator;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.serializer.Jackson2JsonRedisSerializer;
import org.springframework.data.redis.serializer.RedisSerializer;
import org.springframework.http.converter.json.Jackson2ObjectMapperBuilder;

/**
 * 定义Redis的序列化器
 * <p>
 *     Redis的对象序列化使用的是JdkSerializationRedisSerializer，数据在redis格式不方面排查，
 *     此工具类自定义了序列化器。
 * @author 朱俊伟
 * @since 2023/11/02 22:49
 */
@Configuration
@RequiredArgsConstructor
public class RedisConfig {

    private final Jackson2ObjectMapperBuilder jackson2ObjectMapperBuilder;

    @Bean(name = "redisTemplate")
    public RedisTemplate<String, Object> getRedisTemplate(RedisConnectionFactory factory) {
        RedisTemplate<String, Object> redisTemplate = new RedisTemplate<>();
        redisTemplate.setConnectionFactory(factory);

        // 设置RedisTemplate的序列化器
        redisTemplate.setKeySerializer(stringRedisSerializer()); // 设置键的序列化器为字符串序列化器
        redisTemplate.setValueSerializer(jackson2JsonRedisSerializer()); // 设置值的序列化器为JSON序列化器
        redisTemplate.setHashKeySerializer(stringRedisSerializer()); // 设置哈希键的序列化器为字符串序列化器
        redisTemplate.setHashValueSerializer(jackson2JsonRedisSerializer()); // 设置哈希值的序列化器为JSON序列化器

        // 确保所有的属性设置完毕
        redisTemplate.afterPropertiesSet();

        return redisTemplate;
    }

    @Bean
    public RedisSerializer<String> stringRedisSerializer() {
        return RedisSerializer.string();
    }

    @Bean
    public Jackson2JsonRedisSerializer<Object> jackson2JsonRedisSerializer() {
        ObjectMapper objectMapper = jackson2ObjectMapperBuilder.createXmlMapper(false).build()
                .setVisibility(PropertyAccessor.ALL, JsonAutoDetect.Visibility.ANY)
                .activateDefaultTyping(LaissezFaireSubTypeValidator.instance,
                        ObjectMapper.DefaultTyping.NON_FINAL, JsonTypeInfo.As.PROPERTY);
        return new Jackson2JsonRedisSerializer<>(objectMapper, Object.class);
    }
}