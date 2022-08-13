package com.luanvv.microservices.bookstore.product_import

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication

@SpringBootApplication
class ProductImportApplication

fun main(args: Array<String>) {
	runApplication<ProductImportApplication>(*args)
}

