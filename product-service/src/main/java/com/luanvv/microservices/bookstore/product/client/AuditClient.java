package com.luanvv.microservices.bookstore.product.client;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

@FeignClient("audit")
public interface AuditClient {

	@PostMapping("/internal/v1/publish")
	String publish(@RequestBody RequestMessage requestMessage);

}