package com.luanvv.microservices.bookstore.louisstore.configuration

import com.luanvv.bookstore.product.client.ApiClient
import com.luanvv.bookstore.product.client.api.ProductApi
import org.springframework.beans.factory.annotation.Value
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.web.client.RestTemplate

@Configuration
class ProductClientConfiguration(
    @Value("\${rest.api.product-service:}")
    private val basePath: String,
) {

    @Bean
    fun productApi(
        restTemplate: RestTemplate,
    ): ProductApi {
        val apiClient = ApiClient(restTemplate)
        apiClient.basePath = basePath
        return ProductApi(apiClient)
    }
}