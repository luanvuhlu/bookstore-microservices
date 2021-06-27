package com.luanvv.microservices.audit.persistent;

import java.time.LocalDateTime;
import java.util.UUID;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.PrePersist;
import javax.persistence.Table;

import org.springframework.data.annotation.CreatedDate;

import lombok.Data;

@Table
@Entity
@Data
public class AuditMessage {

	@Id
	private String id;
	private String serviceName;
	private String eventAction;
	private String privilege;
	private String userId;
	private String description;
	@CreatedDate
	private LocalDateTime createdDate;

	@PrePersist
	private void init() {
		this.id = UUID.randomUUID().toString();
	}
}
