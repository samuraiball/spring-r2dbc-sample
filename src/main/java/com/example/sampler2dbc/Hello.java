package com.example.sampler2dbc;

import java.io.Serializable;

public class Hello implements Serializable {

	private String hello;

	Hello(){
	}

	public Hello(String hello){
		this.hello = hello;
	}

	public String getHello() {
		return hello;
	}

	public void setHello(String hello) {
		this.hello = hello;
	}
}
