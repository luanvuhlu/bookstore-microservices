package com.luanvv.microservices.audit.rest.handler;

import org.springframework.http.MediaType;
import org.springframework.http.ReactiveHttpInputMessage;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.BodyExtractor;
import org.springframework.web.reactive.function.BodyInserters;
import org.springframework.web.reactive.function.server.ServerRequest;
import org.springframework.web.reactive.function.server.ServerResponse;

import com.luanvv.microservices.audit.event.EventPublisher;
import com.luanvv.microservices.audit.model.RequestMessage;

import lombok.RequiredArgsConstructor;
import reactor.core.publisher.Mono;

@Component
@RequiredArgsConstructor
public class AuditHandler {

	private final EventPublisher eventPublisher;
	
  public Mono<ServerResponse> publish(ServerRequest request) {
	  request.body(new BodyExtractor<RequestMessage, ReactiveHttpInputMessage>() {

		@Override
		public RequestMessage extract(ReactiveHttpInputMessage inputMessage, Context context) {
			// TODO Auto-generated method stub
			return null;
		}
		  
	  });
    return ServerResponse.ok().contentType(MediaType.TEXT_PLAIN)
      .body(BodyInserters.fromValue("Success"));
  }
}