package com.bootcamp.currency.config;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.data.redis.connection.ReactiveRedisConnectionFactory;
import org.springframework.data.redis.connection.RedisPassword;
import org.springframework.data.redis.connection.RedisStandaloneConfiguration;
import org.springframework.data.redis.core.ReactiveRedisOperations;
import org.springframework.data.redis.core.ReactiveRedisTemplate;
import org.springframework.data.redis.serializer.Jackson2JsonRedisSerializer;
import org.springframework.data.redis.serializer.RedisSerializationContext;
import org.springframework.data.redis.serializer.StringRedisSerializer;

import com.bootcamp.currency.domain.Currency;

import lombok.Setter;

@Configuration
@ConfigurationProperties(prefix = "spring.redis")
@Setter
public class RedisConfiguration {
	private String host;
    private String password;
  	@Bean
  	@Primary
    ReactiveRedisOperations<String, Currency> reactiveRedisOperations(ReactiveRedisConnectionFactory factory) 
    //ReactiveRedisOperations<String, Currency> redisOperations(ReactiveRedisConnectionFactory factory)
  	{
  	   Jackson2JsonRedisSerializer<Currency> serializer = new Jackson2JsonRedisSerializer<>(Currency.class);

  	    RedisSerializationContext.RedisSerializationContextBuilder<String, Currency> builder =
  	        RedisSerializationContext.newSerializationContext(new StringRedisSerializer());

  	    RedisSerializationContext<String, Currency> context = builder.value(serializer).build();

        return new ReactiveRedisTemplate<>(factory, context);
    }
 
}
