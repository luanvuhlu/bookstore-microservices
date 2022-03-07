package com.luanvv.microservices.bookstore.product.config;

import java.util.concurrent.Executor;
import org.modelmapper.ModelMapper;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;

@Configuration
@EnableAsync
@EnableJpaAuditing
public class AppConfig {

	@Bean
	public ModelMapper modelMapper() {
	    return new ModelMapper();
	}
	
	@Bean(name = "com.luanvv.microservices.bookstore.product.config.AppConfig.threadPoolTaskExecutor")
	public Executor threadPoolTaskExecutor() {
        return new ThreadPoolTaskExecutor();
    }
}
