package com.luanvv.microservices.audit.persistent;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface AuditMessageRepository extends JpaRepository<AuditMessage, String> {

}
