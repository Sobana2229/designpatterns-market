package com.designpatterns.market.facade;

import java.util.logging.Logger;

public class InventoryService {
    private final Logger logger = Logger.getLogger(InventoryService.class.getName());

    public boolean reserve(String productCode, int qty) {
        logger.info("Reserving " + qty + " of " + productCode);
        if (productCode == null || productCode.isBlank()) return false;
        if (qty <= 0) return false;
        // simplified: always succeed
        return true;
    }
}
