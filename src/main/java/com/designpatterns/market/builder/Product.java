package com.designpatterns.market.builder;

public class Product {
    private final String name;
    private final String code;
    private final int credits;
    private final double price;
    private final String origin;

    Product(String name, String code, int credits, double price, String origin) {
        this.name = name; this.code = code; this.credits = credits; this.price = price; this.origin = origin;
    }

    public String getName() { return name; }
    public String getCode() { return code; }
    public int getCredits() { return credits; }
    public double getPrice() { return price; }
    public String getOrigin() { return origin; }

    @Override
    public String toString() {
        return String.format("Product{name='%s', code='%s', credits=%d, price=%.2f, origin='%s'}", name, code, credits, price, origin);
    }
}
