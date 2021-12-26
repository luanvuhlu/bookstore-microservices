package com.luanvv.microservices.orderservice.handlers;

import com.luanvv.microservices.orderservice.models.Product;
import com.luanvv.microservices.orderservice.models.ProductEvent;
import com.luanvv.microservices.orderservice.repositories.ProductRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.BodyInserters;
import org.springframework.web.reactive.function.client.WebClient;
import org.springframework.web.reactive.function.server.ServerRequest;
import org.springframework.web.reactive.function.server.ServerResponse;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.time.Duration;

@Component
@RequiredArgsConstructor
public class ProductHandler {

    private final ProductRepository repository;

    public Mono<ServerResponse> getAllProducts(ServerRequest request) {
        var products = repository.findAll();
        return ServerResponse.ok()
                .contentType(MediaType.APPLICATION_JSON)
                .body(products, Product.class);
    }

    public Mono<ServerResponse> getProduct(ServerRequest request) {
        var id = request.pathVariable("id");
        var productMono = repository.findById(id);
        return productMono.flatMap(product -> ServerResponse.ok()
                .contentType(MediaType.APPLICATION_JSON)
                .body(BodyInserters.fromValue(product))
        ).switchIfEmpty(ServerResponse.notFound().build());
    }

    public Mono<ServerResponse> createProduct(ServerRequest request) {
        var productMono = request.bodyToMono(Product.class);
        return productMono.flatMap(product ->
                ServerResponse.status(HttpStatus.CREATED)
                        .contentType(MediaType.APPLICATION_JSON)
                        .body(repository.save(product), Product.class));
    }

    public Mono<ServerResponse> updateProduct(ServerRequest request) {
        var id = request.pathVariable("id");
        var existingProductMono = repository.findById(id);
        var productMono = request.bodyToMono(Product.class);
        return productMono.zipWith(existingProductMono,
                (product, existingProduct) -> new Product(existingProduct.getId(), product.getName(), product.getPrice()))
                .flatMap(product ->
                        ServerResponse.ok()
                                .contentType(MediaType.APPLICATION_JSON)
                                .body(repository.save(product), Product.class)
                ).switchIfEmpty(ServerResponse.notFound().build());
    }

    public Mono<ServerResponse> getProductEvents(ServerRequest request) {
        var eventsFlux = Flux.interval(Duration.ofSeconds(1))
                .map(val -> new ProductEvent(val, "Product Event"));
        return ServerResponse.ok()
                .contentType(MediaType.APPLICATION_JSON)
                .body(eventsFlux, ProductEvent.class);
    }

    public Mono<ServerResponse> getProductsByWebClient(ServerRequest request) {
        var webClient = WebClient.builder()
                .baseUrl("http://localhost:57337/products")
                .build();
        Flux<Product> productFlux = webClient.get()
                .exchangeToFlux(response -> response.bodyToFlux(Product.class));
        return ServerResponse.ok()
                .contentType(MediaType.APPLICATION_JSON)
                .body(productFlux, Product.class);
    }
}
