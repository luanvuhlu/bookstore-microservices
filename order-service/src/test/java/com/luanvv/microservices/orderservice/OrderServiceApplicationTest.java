package com.luanvv.microservices.orderservice;

import org.junit.jupiter.api.Test;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.time.Duration;

class OrderServiceApplicationTest {

    @Test
    void testMono() throws InterruptedException {
        Flux<Integer> fluxTen = Mono.just(10)
                .flatMapMany(i -> Flux.range(1, i))
                .delayElements(Duration.ofMillis(300));
        Flux<Integer> fluxThree = Mono.just(3)
                .flatMapMany(i -> Flux.range(1, i))
                .delayElements(Duration.ofMillis(200));
        Flux.zip(fluxTen, fluxThree, (i, j) -> i + "-" + j).subscribe(System.out::println);
        Thread.sleep(3600);
    }
}