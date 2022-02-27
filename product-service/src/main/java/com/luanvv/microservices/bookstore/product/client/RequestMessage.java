package com.luanvv.microservices.bookstore.product.client;

import java.time.LocalDateTime;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class RequestMessage {

	private String id;
	private String serviceName = "bookstore";
	private String eventAction;
	private String privilege;
	private String userId;
	private String description;
	private LocalDateTime createdDate;
	public RequestMessage(String eventAction) {
		super();
		this.eventAction = eventAction;
	}
	
}
