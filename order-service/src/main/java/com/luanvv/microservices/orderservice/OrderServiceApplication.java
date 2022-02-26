package com.luanvv.microservices.orderservice;

import com.luanvv.microservices.orderservice.handlers.ProductHandler;
import org.springframework.boot.LazyInitializationBeanFactoryPostProcessor;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Import;
import org.springframework.http.MediaType;
import org.springframework.web.reactive.function.server.RouterFunction;
import org.springframework.web.reactive.function.server.ServerResponse;

import static org.springframework.web.reactive.function.server.RequestPredicates.accept;
import static org.springframework.web.reactive.function.server.RouterFunctions.route;

@SpringBootApplication
@EnableEurekaClient
@EnableFeignClients
@Import({LazyInitializationBeanFactoryPostProcessor.class})
public class OrderServiceApplication {

	public static void main(String[] args) {
		SpringApplication.run(OrderServiceApplication.class, args);
	}

	@Bean
	RouterFunction<ServerResponse> routes(ProductHandler handler) {
		return route()
				.GET("/products-route/products/webClient", accept(MediaType.APPLICATION_JSON), handler::getProductsByWebClient)
				.GET("/products-route/products/events", accept(MediaType.TEXT_EVENT_STREAM), handler::getProductEvents)
				.GET("/products-route/products/{id}", accept(MediaType.APPLICATION_JSON), handler::getProduct)
				.GET("/products-route/products", accept(MediaType.APPLICATION_JSON), handler::getAllProducts)
				.POST("/products-route/products", accept(MediaType.APPLICATION_JSON), handler::createProduct)
				.PUT("/products-route/products/{id}", accept(MediaType.APPLICATION_JSON), handler::createProduct)
				.build()
				;
	}
}
