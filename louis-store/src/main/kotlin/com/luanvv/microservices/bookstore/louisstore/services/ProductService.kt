package com.luanvv.microservices.bookstore.louisstore.services

import com.luanvv.bookstore.product.client.api.ProductApi
import com.luanvv.bookstore.product.client.model.Product
import com.luanvv.bookstore.product.client.model.ProductsList
import org.springframework.http.ResponseEntity
import org.springframework.stereotype.Service

@Service
class ProductService(
    val productsApi: ProductApi
) {
    fun listProducts(
        page: Int,
        size: Int,
    ): ProductsList {
        return productsApi.getProducts(page, size)
    }

    fun getProduct(productId: String): Product {
        return productsApi.getProduct(productId)
    }
}