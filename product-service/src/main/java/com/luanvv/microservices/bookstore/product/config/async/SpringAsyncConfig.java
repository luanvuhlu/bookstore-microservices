package com.luanvv.microservices.bookstore.product.config.async;

import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.AsyncConfigurerSupport;
import org.springframework.scheduling.annotation.EnableAsync;

@Configuration
@EnableAsync
public class SpringAsyncConfig extends AsyncConfigurerSupport {

  public static final String BEAN_NAME = "com.luanvv.microservices.bookstore.product.config.SpringAsyncConfig.threadPoolTaskExecutor";

//  @Override
//  @Bean(name = BEAN_NAME)
//  public Executor getAsyncExecutor() {
//    var executor = new ThreadPoolTaskExecutor();
//    executor.setCorePoolSize(7);
//    executor.setMaxPoolSize(42);
//    executor.setQueueCapacity(11);
//    executor.setThreadNamePrefix("BookStoreExecutor-");
//    executor.initialize();
//    return executor;
//  }

}
