package com.luanvv.microservices.audit.rest;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import com.luanvv.microservices.audit.event.EventPublisher;
import com.luanvv.microservices.audit.model.RequestMessage;

import lombok.RequiredArgsConstructor;

@Controller
@RequestMapping("/internal/v1")
@RequiredArgsConstructor
public class AuditController {

	private final EventPublisher eventPublisher;
	
	@PostMapping("/publish")
	public String publish(@RequestBody RequestMessage requestMessage) {
		eventPublisher.publish(requestMessage);
		return "OK";
	}
}
