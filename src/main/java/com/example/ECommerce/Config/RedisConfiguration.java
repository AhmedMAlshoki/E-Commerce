package com.example.ECommerce.Config;


import com.example.ECommerce.RedisHash.Cart;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.cache.RedisCacheConfiguration;
import org.springframework.data.redis.cache.RedisCacheManager;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.data.redis.connection.jedis.JedisConnectionFactory;
import org.springframework.data.redis.core.RedisKeyValueAdapter;
import org.springframework.data.redis.core.RedisKeyValueTemplate;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.mapping.RedisMappingContext;
import org.springframework.data.redis.serializer.Jackson2JsonRedisSerializer;
import org.springframework.data.redis.serializer.RedisSerializationContext;
import org.springframework.data.redis.serializer.StringRedisSerializer;

import java.time.Duration;

@Configuration
public class RedisConfiguration {

    @Bean
    public RedisCacheManager cacheManager(RedisConnectionFactory jedisConnectionFactory) {
        RedisCacheConfiguration defaultCacheConfig = RedisCacheConfiguration.defaultCacheConfig()
                .entryTtl(Duration.ofDays(1))
                .disableCachingNullValues()
                .serializeValuesWith(RedisSerializationContext.SerializationPair.fromSerializer(new Jackson2JsonRedisSerializer<Cart>(Cart.class)));
        return RedisCacheManager.builder(jedisConnectionFactory)
                .cacheDefaults(defaultCacheConfig).build();
    }
    @Bean
    RedisConnectionFactory jedisConnectionFactory() {
        return new JedisConnectionFactory();
    }



    @Bean
    public RedisKeyValueTemplate redisKeyValueTemplate(RedisKeyValueAdapter redisAdapter, RedisMappingContext mappingContext) {
        return new RedisKeyValueTemplate(redisAdapter,mappingContext);
    }

    @Bean
    public RedisTemplate<String, Cart> redisTemplate() {
        RedisTemplate<String, Cart> template = new RedisTemplate<>();
        template.setConnectionFactory(jedisConnectionFactory());
        template.setKeySerializer(new StringRedisSerializer());
        template.setHashKeySerializer(new StringRedisSerializer());
        template.setHashValueSerializer(new Jackson2JsonRedisSerializer<>(Object.class));
        template.setValueSerializer(new Jackson2JsonRedisSerializer<>(Object.class));
        return template;
    }
}
