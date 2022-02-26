package com.luanvv.microservices.orderservice.handlers;

import com.luanvv.microservices.orderservice.models.Product;
import com.luanvv.microservices.orderservice.repositories.ProductRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.web.reactive.server.WebTestClient;
import org.springframework.web.reactive.function.server.RouterFunction;

import java.util.List;

@TestPropertySource(properties = "spring.mongodb.embedded.version=3.5.5")
@SpringBootTest
class ProductHandlerTest {

    private WebTestClient client;

    private List<Product> expectedList;

    @Autowired
    private ProductRepository repository;

    @Autowired
    private RouterFunction routes;

    @BeforeEach
    void beforeEach() {
        this.client = WebTestClient.bindToRouterFunction(routes)
                .configureClient()
                .baseUrl("/products-route/products/")
                .build();

        this.expectedList = repository.findAll().collectList().block();
    }

    @Test
    void testGetAllProducts() {
        client
                .get()
                .uri("/")
                .exchange()
                .expectStatus()
                .isOk()
                .expectBodyList(Product.class)
                .isEqualTo(expectedList);
    }
}