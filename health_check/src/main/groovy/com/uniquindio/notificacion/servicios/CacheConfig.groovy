package com.uniquindio.notificacion.servicios

import org.springframework.cache.CacheManager
import org.springframework.cache.annotation.EnableCaching
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.data.redis.cache.RedisCacheConfiguration
import org.springframework.data.redis.cache.RedisCacheManager
import org.springframework.data.redis.connection.lettuce.LettuceConnectionFactory
import org.springframework.data.redis.core.RedisTemplate

import java.time.Duration

@Configuration
@EnableCaching
class RedisCacheConfig {

    @Bean
    LettuceConnectionFactory redisConnectionFactory() {
        return new LettuceConnectionFactory()
    }

    @Bean
    RedisTemplate<Object, Object> redisTemplate(LettuceConnectionFactory connectionFactory) {
        RedisTemplate<Object, Object> template = new RedisTemplate<>()
        template.setConnectionFactory(connectionFactory)
        return template
    }

    @Bean
    CacheManager cacheManager(LettuceConnectionFactory connectionFactory) {
        RedisCacheConfiguration redisCacheConfiguration = RedisCacheConfiguration.defaultCacheConfig()
                .entryTtl(Duration.ofMinutes(10)) // Expiración de los elementos en la caché
        return RedisCacheManager.builder(connectionFactory)
                .cacheDefaults(redisCacheConfiguration)
                .build()
    }
}


