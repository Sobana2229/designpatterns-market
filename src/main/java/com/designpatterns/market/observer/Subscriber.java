package com.designpatterns.market.observer;

public interface Subscriber {
    void update(String item, double price);
    String getName();
}
