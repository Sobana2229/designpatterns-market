package com.designpatterns.market.strategy;

public class BulkDiscount implements DiscountStrategy {
    private final int minQty;
    private final double percent;

    public BulkDiscount(int minQty, double percent) {
        if (minQty <= 0) throw new IllegalArgumentException("minQty must be > 0");
        if (percent < 0 || percent > 100) throw new IllegalArgumentException("percent must be 0..100");
        this.minQty = minQty;
        this.percent = percent;
    }

    @Override
    public double apply(double basePrice) {
        // client will check quantity; for demo we assume qty>=minQty
        return basePrice * (1 - percent / 100.0);
    }

    @Override
    public String name() { return "BulkDiscount(min=" + minQty + ", %=" + percent + ")"; }
}
