package com.luanvv.microservices.audit.model;

import java.time.LocalDateTime;

import lombok.Data;

@Data
public class RequestMessage {

	private String id;
	private String serviceName;
	private String eventAction;
	private String privilege;
	private String userId;
	private String description;
	private LocalDateTime createdDate;
}
