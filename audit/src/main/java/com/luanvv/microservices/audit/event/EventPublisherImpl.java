package com.luanvv.microservices.audit.event;

import com.luanvv.bookstore.audit.specs.model.RequestMessageModel;
import com.luanvv.microservices.audit.model.RequestMessage;
import com.luanvv.microservices.audit.persistent.AuditMessage;
import com.luanvv.microservices.audit.persistent.AuditMessageService;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.Future;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Component;
import reactor.core.publisher.Sinks.Many;

@Component
@RequiredArgsConstructor
public class EventPublisherImpl implements EventPublisher {

	private final Many<RequestMessage> auditFlux;

	private final AuditMessageService service;

	private final ModelMapper modelMapper;

	@Override
	@Async
	public Future<Void> publish(RequestMessageModel message) {
		auditFlux.tryEmitNext(modelMapper.map(message, RequestMessage.class));
		service.save(modelMapper.map(message, AuditMessage.class));
		return CompletableFuture.completedFuture(null);
	}
}
