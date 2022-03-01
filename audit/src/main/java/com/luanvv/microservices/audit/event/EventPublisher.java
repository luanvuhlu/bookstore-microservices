package com.luanvv.microservices.audit.event;

import com.luanvv.bookstore.audit.specs.model.RequestMessageModel;
import java.util.concurrent.Future;

public interface EventPublisher {

	Future<Void> publish(RequestMessageModel requestMessage);

}
