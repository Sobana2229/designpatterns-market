package com.designpatterns.market.factory;

public class TransportFactory {
    public enum TransportType { TRUCK, BIKE }

    public static Transport create(TransportType type) {
        switch (type) {
            case TRUCK: return new Truck();
            case BIKE: return new Bike();
            default: throw new IllegalArgumentException("Unsupported transport type: " + type);
        }
    }
}
