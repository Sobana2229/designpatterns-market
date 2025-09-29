package com.designpatterns.market.factory;

public interface Transport {
    void deliver(String payload);
    String type();
}
