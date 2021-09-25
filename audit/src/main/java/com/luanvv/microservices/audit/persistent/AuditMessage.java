package com.luanvv.microservices.audit.persistent;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.hibernate.annotations.GenericGenerator;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.*;
import java.time.LocalDateTime;

@Table
@EntityListeners(AuditingEntityListener.class)
@Entity
@ToString @Getter @Setter
public class AuditMessage {

	@Id
	@GeneratedValue(generator = "uuid")
	@GenericGenerator(name = "uuid", strategy = "uuid")
	@Column(columnDefinition = "CHAR(32)")
	private String id;
	private String serviceName;
	private String eventAction;
	private String privilege;
	private String userId;
	private String description;
	@CreatedDate
	private LocalDateTime createdDate;
}