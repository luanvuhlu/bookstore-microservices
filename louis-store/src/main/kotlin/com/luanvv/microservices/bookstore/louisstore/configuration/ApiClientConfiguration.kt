package com.luanvv.microservices.bookstore.louisstore.configuration

import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.http.client.BufferingClientHttpRequestFactory
import org.springframework.web.client.RestTemplate

@Configuration
class ApiClientConfiguration {

    @Bean
    fun restTemplate(): RestTemplate {
        val restTemplate = RestTemplate()
        restTemplate.requestFactory = BufferingClientHttpRequestFactory(restTemplate.requestFactory)
        return restTemplate
    }
}