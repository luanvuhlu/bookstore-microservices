package com.luanvv.microservices.audit.event;

import java.util.concurrent.Future;

import com.luanvv.microservices.audit.model.RequestMessage;

public interface EventPublisher {

	Future<Void> publish(RequestMessage requestMessage);

}
