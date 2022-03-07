package com.luanvv.microservices.bookstore.product.config.rest.client;

import com.luanvv.bookstore.audit.client.ApiClient;
import com.luanvv.bookstore.audit.client.api.AuditApi;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class AuditRestAutoConfiguration {

  @Bean
  public AuditApi auditApi(@Value("${rest.api.audit}") String basePath) {
    var apiClient = new ApiClient();
    apiClient.setBasePath(basePath);
    return new AuditApi(apiClient);
  }
}
