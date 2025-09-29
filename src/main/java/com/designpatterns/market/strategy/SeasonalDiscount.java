package com.designpatterns.market.strategy;

public class SeasonalDiscount implements DiscountStrategy {
    private final double percent;

    public SeasonalDiscount(double percent) {
        if (percent < 0 || percent > 100) throw new IllegalArgumentException("percent must be 0..100");
        this.percent = percent;
    }

    @Override
    public double apply(double basePrice) {
        return basePrice * (1 - percent / 100.0);
    }

    @Override
    public String name() { return "SeasonalDiscount(" + percent + "% )"; }
}
