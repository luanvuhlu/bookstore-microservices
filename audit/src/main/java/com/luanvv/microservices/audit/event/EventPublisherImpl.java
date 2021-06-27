package com.luanvv.microservices.audit.event;

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.Future;

import org.modelmapper.ModelMapper;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Component;

import com.luanvv.microservices.audit.model.RequestMessage;
import com.luanvv.microservices.audit.persistent.AuditMessage;
import com.luanvv.microservices.audit.persistent.AuditMessageService;

import lombok.RequiredArgsConstructor;
import reactor.core.publisher.Sinks.Many;

@Component
@RequiredArgsConstructor
public class EventPublisherImpl implements EventPublisher {

	private final Many<RequestMessage> auditFlux;

	private final AuditMessageService service;

	private final ModelMapper modelMapper;

	@Override
	@Async
	public Future<Void> publish(RequestMessage message) {
		auditFlux.tryEmitNext(message);
		service.save(modelMapper.map(message, AuditMessage.class));
		return CompletableFuture.completedFuture(null);
	}
}
