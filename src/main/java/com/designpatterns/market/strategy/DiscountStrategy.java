package com.designpatterns.market.strategy;

public interface DiscountStrategy {
    double apply(double basePrice);
    String name();
}
