package com.example.sampler2dbc;

import io.r2dbc.spi.ConnectionFactories;
import io.r2dbc.spi.ConnectionFactoryOptions;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContextInitializer;
import org.springframework.context.support.GenericApplicationContext;
import io.r2dbc.spi.ConnectionFactory;
import org.springframework.data.r2dbc.connectionfactory.R2dbcTransactionManager;
import org.springframework.data.r2dbc.core.DatabaseClient;
import org.springframework.transaction.ReactiveTransactionManager;
import org.springframework.web.reactive.function.server.RouterFunction;

import static io.r2dbc.spi.ConnectionFactoryOptions.*;

@SpringBootApplication
public class SampleR2dbcApplication implements ApplicationContextInitializer<GenericApplicationContext> {

	public static void main(String[] args) {
		SpringApplication.run(SampleR2dbcApplication.class, args);
	}

	@Override
	public void initialize(GenericApplicationContext context) {
		context.registerBean(ConnectionFactory.class, () ->
				ConnectionFactories.get(ConnectionFactoryOptions.builder()
						.option(DRIVER, "h2")
						.option(PROTOCOL, "file")
						.option(DATABASE, "./target/demo")
						.build()
				));

		context.registerBean(DatabaseClient.class, () -> DatabaseClient.create(context.getBean(ConnectionFactory.class)));
		context.registerBean(ReactiveTransactionManager.class, () -> new R2dbcTransactionManager(context.getBean(ConnectionFactory.class)));
		context.registerBean(HelloR2DBCHandler.class);
		context.registerBean(RouterFunction.class, () -> context.getBean(HelloR2DBCHandler.class).routes());
		context.registerBean(InitializingBean.class, () -> () -> context.getBean(DatabaseClient.class)
				//todo:初期データの投入
				//	.insert()
				//	.block()
		);
	}
}
