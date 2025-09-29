package com.designpatterns.market.observer;

import java.util.logging.Logger;

public class FarmerSubscriber implements Subscriber {
    private final String name;
    private final Logger logger = Logger.getLogger(FarmerSubscriber.class.getName());

    public FarmerSubscriber(String name) {
        this.name = name == null ? "anonymous_farmer" : name.trim();
    }

    @Override
    public void update(String item, double price) {
        logger.info("[Farmer:" + name + "] received update: " + item + " @ " + price);
        if (price > 200) {
            logger.info("[Farmer:" + name + "] Good time to sell " + item + " at " + price);
        }
    }

    @Override
    public String getName() {
        return name;
    }
}
