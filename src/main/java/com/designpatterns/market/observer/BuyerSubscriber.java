package com.designpatterns.market.observer;

import java.util.logging.Logger;

public class BuyerSubscriber implements Subscriber {
    private final String name;
    private final Logger logger = Logger.getLogger(BuyerSubscriber.class.getName());

    public BuyerSubscriber(String name) {
        this.name = name == null ? "anonymous_buyer" : name.trim();
    }

    @Override
    public void update(String item, double price) {
        // simple decision: buyer alerts if price < threshold (dummy behaviour)
        logger.info("[Buyer:" + name + "] received update: " + item + " @ " + price);
        if (price < 50) {
            logger.info("[Buyer:" + name + "] Consider buying " + item + " at " + price);
        }
    }

    @Override
    public String getName() {
        return name;
    }
}
