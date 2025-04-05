package com.example.ECommerce.Config;


import com.example.ECommerce.RedisHash.Cart;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.cache.RedisCacheConfiguration;
import org.springframework.data.redis.cache.RedisCacheManager;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.data.redis.connection.jedis.JedisConnectionFactory;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.serializer.Jackson2JsonRedisSerializer;
import org.springframework.data.redis.serializer.RedisSerializationContext;

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
    public RedisTemplate<String, Cart> redisTemplate() {
        RedisTemplate<String, Cart> template = new RedisTemplate<>();
        template.setDefaultSerializer(new Jackson2JsonRedisSerializer<Cart>(Cart.class));
        template.setConnectionFactory(jedisConnectionFactory());
        return template;
    }
}
