package com.designpatterns.market.strategy;

public class NoDiscount implements DiscountStrategy {
    @Override
    public double apply(double basePrice) {
        return basePrice;
    }

    @Override
    public String name() { return "NoDiscount"; }
}
