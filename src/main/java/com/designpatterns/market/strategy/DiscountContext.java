package com.designpatterns.market.strategy;

import java.util.Objects;

public class DiscountContext {
    private DiscountStrategy strategy;

    public DiscountContext(DiscountStrategy strategy) {
        this.strategy = Objects.requireNonNull(strategy, "strategy cannot be null");
    }

    public void setStrategy(DiscountStrategy strategy) {
        this.strategy = Objects.requireNonNull(strategy, "strategy cannot be null");
    }

    public double calculate(double price) {
        return strategy.apply(price);
    }

    public String currentStrategyName() {
        return strategy.name();
    }
}
