package com.luanvv.microservices.bookstore.louisstore

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication
import org.springframework.cloud.netflix.eureka.EnableEurekaClient

@EnableEurekaClient
@SpringBootApplication
class LouisStoreApplication

fun main(args: Array<String>) {
	runApplication<LouisStoreApplication>(*args)
}
