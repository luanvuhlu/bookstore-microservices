package com.luanvv.microservices.audit.persistent;

import org.springframework.data.jpa.repository.JpaRepository;

public interface AuditMessageRepository extends JpaRepository<AuditMessage, String> {

}
