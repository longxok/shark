package com.cloudwalk.shark.config.redisson;

import com.cloudwalk.shark.config.inter.impl.RedissonLocker;
import org.redisson.Redisson;
import org.redisson.api.RedissonClient;
import org.redisson.config.Config;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.io.IOException;


@Configuration
public class RedissonConfig {

    @Value("${mini.redis.host}")
    private String host;
    @Value("${mini.redis.port}")
    private String port;
    @Value("${mini.redis.passwd}")
    private String password;

    /**
     * RedissonClient,单机模式
     * @return
     * @throws IOException
     */
    @Bean(destroyMethod = "shutdown")
    public RedissonClient redisson() throws IOException {
        Config config = new Config();
        config.useSingleServer().setAddress("redis://" + host + ":" + port).setPassword(password);
        return Redisson.create(config);
    }

    @Bean
    public RedissonLocker redissonLocker(RedissonClient redissonClient){
        RedissonLocker locker = new RedissonLocker(redissonClient);
        //设置LockUtil的锁处理对象
        LockUtil.setLocker(locker);
        return locker;
    }
}
