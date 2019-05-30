package com.example.sampler2dbc;

import org.springframework.data.r2dbc.core.DatabaseClient;
import org.springframework.transaction.reactive.TransactionalOperator;
import org.springframework.web.reactive.function.server.RouterFunction;
import org.springframework.web.reactive.function.server.ServerRequest;
import org.springframework.web.reactive.function.server.ServerResponse;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import static org.springframework.web.reactive.function.server.RouterFunctions.route;
import static org.springframework.web.reactive.function.server.ServerResponse.ok;

public class HelloR2DBCHandler {

	private final DatabaseClient dbClient;
	private final TransactionalOperator tsOperator;

	public HelloR2DBCHandler(DatabaseClient dbClient, TransactionalOperator tsOperator) {
		this.dbClient = dbClient;
		this.tsOperator = tsOperator;
	}

	Mono<ServerResponse> hello(ServerRequest req) {
		final Flux<Hello> helloes = dbClient.select()
				.from(Hello.class)
				.as(Hello.class)
				.all();
		return ok().body(helloes, Hello.class);
	}

	public RouterFunction<ServerResponse> routes() {
		return route()
				.GET("/hello", this::hello)
				.build();
	}
}