package com.example.sampler2dbc;

import java.io.Serializable;

public class HelloWorld implements Serializable {

    private String id;
    private String hello;

    HelloWorld() {
    }

    public HelloWorld(String id, String hello) {
        this.id = id;
        this.hello = hello;
    }

    public String getHello() {
        return hello;
    }

    public String getId() {
        return id;
    }


    @Override
    public String toString() {
        return this.hello;
    }

}
