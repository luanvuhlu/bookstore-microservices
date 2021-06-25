package com.luanvv.microservices.audit.persistent;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import lombok.RequiredArgsConstructor;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class AuditMessageServiceImpl implements AuditMessageService {

	private final AuditMessageRepository repository;

	@Override
	@Transactional
	public void save(AuditMessage auditMessage) {
		repository.save(auditMessage);
	}
}
