package com.luanvv.microservices.bookstore.services;

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.Future;
import java.util.function.Supplier;

import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

@Service
public class AsyncService {

	@Async
	public <V> Future<V> run(Supplier<V> runner){
		return CompletableFuture.completedFuture(runner.get());
	}
}
