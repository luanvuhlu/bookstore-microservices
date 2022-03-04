package com.luanvv.microservices.audit.rest;

import com.luanvv.bookstore.audit.specs.api.InternalApi;
import com.luanvv.bookstore.audit.specs.model.RequestMessageModel;
import com.luanvv.microservices.audit.event.EventPublisher;
import javax.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;

@Controller
@RequiredArgsConstructor
public class AuditController implements InternalApi {

	private final EventPublisher eventPublisher;
	
	@Override
	public ResponseEntity<Void> publish(
			@Valid RequestMessageModel requestMessage) {
		eventPublisher.publish(requestMessage);
		return ResponseEntity.ok().build();
	}
}
