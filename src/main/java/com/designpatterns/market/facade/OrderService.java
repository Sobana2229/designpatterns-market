package com.designpatterns.market.facade;

import java.util.logging.Logger;

public class OrderService {
    private final Logger logger = Logger.getLogger(OrderService.class.getName());

    public String createOrder(String productCode, int qty) {
        logger.info("Creating order for " + qty + " x " + productCode);
        return "ORDER-" + System.currentTimeMillis();
    }
}
