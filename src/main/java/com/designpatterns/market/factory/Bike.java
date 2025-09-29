package com.designpatterns.market.factory;

import java.util.logging.Logger;

public class Bike implements Transport {
    private final Logger logger = Logger.getLogger(Bike.class.getName());
    @Override
    public void deliver(String payload) {
        logger.info("Bike delivering: " + payload);
    }
    @Override
    public String type() { return "Bike"; }
}
