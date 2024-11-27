package com.example.redisinspring.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.connection.RedisStandaloneConfiguration;
import org.springframework.data.redis.connection.lettuce.LettuceConnectionFactory;

@Configuration  // Bean을 정의하고 Spring 컨테이너에 등록(Bean을 수동으로 등록)
public class RedisConfig { // Redis 서버 연결 설정

    @Value("${spring.data.redis.host}") // application.yml의 host 설정 값을 host 변수에 할당
    private String host;

    @Value("${spring.data.redis.port}") // application.yml의 port 설정 값을 port 변수에 할당
    private int port;

    @Bean
    public LettuceConnectionFactory redisConnectionFactory() {
        // LettuceConnectionFactory: Lettuce라는 라이브러리를 활용해 Redis 연결을 관리하는 객체를 생성 / RedisConnectionFactory 타입으로 관리됨
        // RedisStandaloneConfiguration: Redis 단일 서버에 대한 정보(host, port)를 설정
        return new LettuceConnectionFactory(new RedisStandaloneConfiguration(host, port));
    }
}
