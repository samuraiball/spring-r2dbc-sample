package com.example.sampler2dbc;

import io.r2dbc.postgresql.PostgresqlConnectionConfiguration;
import io.r2dbc.postgresql.PostgresqlConnectionFactory;
import io.r2dbc.spi.ConnectionFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContextInitializer;
import org.springframework.context.support.GenericApplicationContext;
import org.springframework.data.r2dbc.connectionfactory.R2dbcTransactionManager;
import org.springframework.data.r2dbc.core.DatabaseClient;
import org.springframework.transaction.ReactiveTransactionManager;
import org.springframework.transaction.reactive.TransactionalOperator;
import org.springframework.web.reactive.function.server.RouterFunction;

@SpringBootApplication
public class SampleR2dbcApplication implements ApplicationContextInitializer<GenericApplicationContext> {

	public static void main(String[] args) {
		SpringApplication.run(SampleR2dbcApplication.class, args);
	}

	@Override
	public void initialize(GenericApplicationContext context) {
		// DBの設定
		context.registerBean(ConnectionFactory.class, () ->
				new PostgresqlConnectionFactory(
						PostgresqlConnectionConfiguration.builder()
								.host("localhost")
								.database("postgres")
								.username("postgres")
								.password("pass").build()));
		context.registerBean(DatabaseClient.class,
				() -> DatabaseClient.create(context.getBean(ConnectionFactory.class)));
		context.registerBean(ReactiveTransactionManager.class,
				() -> new R2dbcTransactionManager(context.getBean(ConnectionFactory.class)));
		context.registerBean(TransactionalOperator.class,
				() -> TransactionalOperator.create(context.getBean(ReactiveTransactionManager.class)));

		// ハンドラーの設定
		context.registerBean(HelloR2DBCHandler.class);
		context.registerBean(RouterFunction.class,
				() -> context.getBean(HelloR2DBCHandler.class).routes());
	}
}
