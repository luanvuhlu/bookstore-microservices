package com.luanvv.microservices.bookstore.client;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;

@FeignClient("audit")
public interface AuditClient {

    @GetMapping("/hello")
    String hello();

}