package com.luanvv.microservices.audit.event;

import com.luanvv.microservices.audit.model.RequestMessage;

public interface EventPublisher {

	void publish(RequestMessage message);

}
