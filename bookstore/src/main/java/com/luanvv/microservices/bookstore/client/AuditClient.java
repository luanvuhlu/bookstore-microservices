package com.luanvv.microservices.bookstore.client;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

@FeignClient("audit")
@RequestMapping("/internal/v1")
public interface AuditClient {

	@PostMapping("/publish")
	String publish(@RequestBody RequestMessage requestMessage);

}