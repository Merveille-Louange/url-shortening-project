package com.example.shorten.configs;

import com.example.shorten.dto.UrlDTO;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.connection.RedisStandaloneConfiguration;
import org.springframework.data.redis.connection.jedis.JedisConnectionFactory;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.serializer.Jackson2JsonRedisSerializer;
import org.springframework.data.redis.serializer.StringRedisSerializer;

@Configuration
public class RedisConfig {

    @Value("${spring.redis.host:localhost}")
    private String redisHost;

    @Value("${spring.redis.username}")
    private String username;

    @Value("${spring.redis.password}")
    private String password;

    @Value("${spring.redis.port:6379}")
    private int redisPort;
    @Autowired
    ObjectMapper mapper;

//    @Autowired
//    RedisConnectionFactory connectionFactory;

    @Bean
    public JedisConnectionFactory redisConnectionFactory() {

//        RedisStandaloneConfiguration redisStandaloneConfiguration = new RedisStandaloneConfiguration(redisHost,
//                redisPort);
//        if(!username.isEmpty()){
//            System.out.println("SETTING REDIS USERNAME AND PASSWORD");
//            redisStandaloneConfiguration.setUsername(username);
//            redisStandaloneConfiguration.setPassword(password);
//
//        }
//
//        redisStandaloneConfiguration.setDatabase(0);
//        return new JedisConnectionFactory(redisStandaloneConfiguration);
        JedisConnectionFactory factory = new JedisConnectionFactory();
        factory.setHostName(redisHost);
        factory.setPort(redisPort);
        factory.setUsePool(true);

        return factory;
    }

    @Bean
    RedisTemplate<String, Object> redisTemplate() {
        final RedisTemplate<String, Object> redisTemplate = new RedisTemplate<>();
        Jackson2JsonRedisSerializer valueSerializer = new Jackson2JsonRedisSerializer(UrlDTO.class);
        valueSerializer.setObjectMapper(mapper);
        redisTemplate.setConnectionFactory(redisConnectionFactory());
        redisTemplate.setKeySerializer(new StringRedisSerializer());
        redisTemplate.setValueSerializer(valueSerializer);
        return redisTemplate;
    }
}