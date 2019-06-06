package com.cloudwalk.shark.config.redis;

import com.alibaba.fastjson.JSONObject;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cache.CacheManager;
import org.springframework.cache.annotation.CachingConfigurerSupport;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.cache.interceptor.KeyGenerator;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.cache.RedisCacheConfiguration;
import org.springframework.data.redis.cache.RedisCacheManager;
import org.springframework.data.redis.cache.RedisCacheWriter;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.data.redis.connection.jedis.JedisConnectionFactory;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.serializer.GenericJackson2JsonRedisSerializer;
import redis.clients.jedis.JedisPoolConfig;

import java.lang.reflect.Method;
import java.time.Duration;

@EnableCaching
@Configuration
public class RedisConfiguration extends CachingConfigurerSupport {

    @Value("${mini.redis.port}")
    private Integer port = 6379;

    @Value("${mini.redis.host}")
    private String host;

    @Value("${mini.redis.maxIdle}")
    private Integer maxIdle;

    @Value("${mini.redis.maxActive}")
    private Integer maxActive;

    @Value("${mini.redis.maxWait}")
    private Integer maxWait;

    @Value("${mini.redis.testOnBorrow}")
    private Boolean testOnBorrow;

    @Value("${mini.redis.passwd}")
    private String password;

    @Value("${mini.redis.expire.time}")
    private Integer timeout;

    @Bean
    public JedisPoolConfig jedisPoolConfig() {
        JedisPoolConfig poolConfig = new JedisPoolConfig();
        poolConfig.setMaxIdle(maxIdle);
        poolConfig.setMaxWaitMillis(maxWait);
        poolConfig.setMaxTotal(maxActive);
        poolConfig.setTestOnBorrow(testOnBorrow);
        return poolConfig;
    }

    @Bean
    public JedisConnectionFactory jedisConnectionFactory(JedisPoolConfig jedisPoolConfig) {
        JedisConnectionFactory connectionFactory = new JedisConnectionFactory();
        connectionFactory.setPoolConfig(jedisPoolConfig);
        connectionFactory.setHostName(host);
        connectionFactory.setPort(port);
        connectionFactory.setPassword(password);
        connectionFactory.setTimeout(timeout);
        return connectionFactory;
    }

    @Override
    @Bean
    public KeyGenerator keyGenerator() {
        return new KeyGenerator() {
            public Object generate(Object target, Method method, Object... params) {
                StringBuilder key = new StringBuilder(":");
                key.append(target.getClass().getName()).append(":");
                key.append(method.getName());
                for (Object param : params) {
                    key.append(JSONObject.toJSONString(param).hashCode()).append("&");
                }
                String keyStr = key.toString();
                if (keyStr.endsWith("&")) {
                    keyStr = keyStr.substring(0, keyStr.length() - 1);
                }
                return keyStr;
            }
        };
    }
    /**
     * 默认的redisCacheManager
     * @param redisTemplate 通过参数注入，这里没有手动给它做配置。在引入了redis的jar包，并且往
     * application.yml里添加了spring.redis的配置项，springboot的autoconfig会自动生成一个
     * redisTemplate的bean
     * @return
     */
    @Bean
    public CacheManager cacheManager(RedisConnectionFactory redisConnectionFactory) {
        RedisCacheConfiguration redisCacheConfiguration = RedisCacheConfiguration.defaultCacheConfig()
                .entryTtl(Duration.ofHours(1)); // 设置缓存有效期一小时
        return RedisCacheManager
                .builder(RedisCacheWriter.nonLockingRedisCacheWriter(redisConnectionFactory))
                .cacheDefaults(redisCacheConfiguration).build();
    }

    @Bean
    public RedisTemplate<String, String> redisTemplate(JedisConnectionFactory jedisConnectionFactory) {
        RedisTemplate<String, String> redisTemplate = new RedisTemplate<String, String>();
        redisTemplate.setConnectionFactory(jedisConnectionFactory);
        redisTemplate.setDefaultSerializer(new GenericJackson2JsonRedisSerializer());
        redisTemplate.afterPropertiesSet();
        return redisTemplate;
    }
}
