package com.example.sampler2dbc;

public class HelloWorldBuilder {
	private String id;
	private String hello;

	public HelloWorldBuilder withId(String id) {
		this.id = id;
		return this;
	}

	public HelloWorldBuilder withHello(String hello) {
		this.hello = hello;
		return this;
	}

	public HelloWorld createHelloWorld() {
		return new HelloWorld(id, hello);
	}
}