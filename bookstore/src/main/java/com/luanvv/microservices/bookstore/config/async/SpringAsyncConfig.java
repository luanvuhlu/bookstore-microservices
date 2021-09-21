package com.luanvv.microservices.bookstore.config.async;

import java.util.concurrent.Executor;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.AsyncConfigurerSupport;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;

@Configuration
@EnableAsync
public class SpringAsyncConfig extends AsyncConfigurerSupport {

	@Override
	@Bean(name = "com.luanvv.microservices.bookstore.config.SpringAsyncConfig.threadPoolTaskExecutor")
    public Executor getAsyncExecutor() {
		var executor = new ThreadPoolTaskExecutor();
        executor.setCorePoolSize(7);
        executor.setMaxPoolSize(42);
        executor.setQueueCapacity(11);
        executor.setThreadNamePrefix("BookStoreExecutor-");
        executor.initialize();
        return executor;
    }
	
}
