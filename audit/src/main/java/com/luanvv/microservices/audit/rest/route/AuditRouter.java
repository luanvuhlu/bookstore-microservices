package com.luanvv.microservices.audit.rest.route;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.MediaType;
import org.springframework.web.reactive.function.server.RequestPredicates;
import org.springframework.web.reactive.function.server.RouterFunction;
import org.springframework.web.reactive.function.server.RouterFunctions;
import org.springframework.web.reactive.function.server.ServerResponse;

import com.luanvv.microservices.audit.rest.handler.AuditHandler;

@Configuration
public class AuditRouter {

  @Bean
  public RouterFunction<ServerResponse> route(AuditHandler auditHandler) {
    return RouterFunctions
      .route(RequestPredicates.POST("/internal/v1/publish")
    		  .and(RequestPredicates.accept(MediaType.APPLICATION_JSON)),
    		  auditHandler::publish);
  }
}