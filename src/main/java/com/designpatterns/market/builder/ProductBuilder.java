package com.designpatterns.market.builder;

import java.util.Objects;

public class ProductBuilder {
    private String name;
    private String code;
    private int credits = 0;
    private double price = 0.0;
    private String origin = "unknown";

    public ProductBuilder setName(String name) { this.name = name; return this; }
    public ProductBuilder setCode(String code) { this.code = code; return this; }
    public ProductBuilder setCredits(int credits) { this.credits = credits; return this; }
    public ProductBuilder setPrice(double price) { this.price = price; return this; }
    public ProductBuilder setOrigin(String origin) { this.origin = origin; return this; }

    public Product build() {
        // defensive validation
        Objects.requireNonNull(name, "name required"); 
        Objects.requireNonNull(code, "code required");
        if (credits < 0) throw new IllegalArgumentException("credits must be >=0");
        if (price < 0) throw new IllegalArgumentException("price must be >=0");
        return new Product(name, code, credits, price, origin == null ? "unknown" : origin);
    }
}
