package com.designpatterns.market.factory;

import java.util.logging.Logger;

public class Truck implements Transport {
    private final Logger logger = Logger.getLogger(Truck.class.getName());
    @Override
    public void deliver(String payload) {
        logger.info("Truck delivering: " + payload);
    }
    @Override
    public String type() { return "Truck"; }
}
