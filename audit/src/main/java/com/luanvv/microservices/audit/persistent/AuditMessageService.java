package com.luanvv.microservices.audit.persistent;

public interface AuditMessageService {

	void save(AuditMessage auditMessage);

}
