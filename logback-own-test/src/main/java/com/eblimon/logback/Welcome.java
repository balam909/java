package com.eblimon.logback;

public class Welcome {

    private String hello;
    private String name;

    public Welcome(String hello, String name) {
        this.hello = hello;
        this.name = name;
    }

    public String getHello() {
        return hello;
    }

    public void setHello(String hello) {
        this.hello = hello;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
