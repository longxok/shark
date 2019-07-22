package com.gitee.taven;

import com.fasterxml.jackson.annotation.JsonAutoDetect;
import com.fasterxml.jackson.annotation.PropertyAccessor;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cache.CacheManager;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.cache.RedisCacheConfiguration;
import org.springframework.data.redis.cache.RedisCacheManager;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.data.redis.serializer.GenericJackson2JsonRedisSerializer;
import org.springframework.data.redis.serializer.Jackson2JsonRedisSerializer;
import org.springframework.data.redis.serializer.RedisSerializationContext;
import org.springframework.data.redis.serializer.StringRedisSerializer;

import java.time.Duration;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

@Configuration
@EnableCaching
public class RedisConfig {

	@Value("${cache.default.expire-time}")
	private int defaultExpireTime;
	@Value("${cache.user.expire-time}")
	private int userCacheExpireTime;
	@Value("${cache.user.name}")
	private String userCacheName;

    /**
     * 缓存管理器
     *
     * @param lettuceConnectionFactory
     * @return
     */
	@Bean
	public CacheManager cacheManager(RedisConnectionFactory lettuceConnectionFactory) {
		RedisCacheConfiguration defaultCacheConfig = RedisCacheConfiguration.defaultCacheConfig();
		// 设置缓存管理器管理的缓存的默认过期时间
		defaultCacheConfig = defaultCacheConfig.entryTtl(Duration.ofSeconds(defaultExpireTime))
				// 设置 key为string序列化
				.serializeKeysWith(RedisSerializationContext.SerializationPair.fromSerializer(new StringRedisSerializer()))
				// 设置value为json序列化
				.serializeValuesWith(RedisSerializationContext.SerializationPair.fromSerializer(new GenericJackson2JsonRedisSerializer()))
				// 不缓存空值
				.disableCachingNullValues();

		Set<String> cacheNames = new HashSet<>();
		cacheNames.add(userCacheName);

		// 对每个缓存空间应用不同的配置
		Map<String, RedisCacheConfiguration> configMap = new HashMap<>();
		configMap.put(userCacheName, defaultCacheConfig.entryTtl(Duration.ofSeconds(userCacheExpireTime)));

		RedisCacheManager cacheManager = RedisCacheManager.builder(lettuceConnectionFactory)
				.cacheDefaults(defaultCacheConfig)
				.initialCacheNames(cacheNames)
				.withInitialCacheConfigurations(configMap)
				.build();
		return cacheManager;
	}

    /**
     * 配置 RedisTemplate 序列化策略，与缓存无关
     *
     * @param lettuceConnectionFactory
     * @return
     */
//	@Bean
//	public RedisTemplate<String, String> redisTemplate(RedisConnectionFactory lettuceConnectionFactory) {
//		StringRedisTemplate template = new StringRedisTemplate(lettuceConnectionFactory);
//        template.setValueSerializer(valueSerializer());
//		template.afterPropertiesSet();
//		return template;
//	}
//
//	private Jackson2JsonRedisSerializer valueSerializer() {
//		Jackson2JsonRedisSerializer jackson2JsonRedisSerializer = new Jackson2JsonRedisSerializer(Object.class);
//		ObjectMapper om = new ObjectMapper();
//		om.setVisibility(PropertyAccessor.ALL, JsonAutoDetect.Visibility.ANY);
//		om.enableDefaultTyping(ObjectMapper.DefaultTyping.NON_FINAL);
//		jackson2JsonRedisSerializer.setObjectMapper(om);
//		return jackson2JsonRedisSerializer;
//	}
	
}
