package com.designpatterns.market.facade;

import java.util.Objects;
import java.util.logging.Logger;

public class MarketFacade {
    private final InventoryService inventory;
    private final PaymentService payment;
    private final OrderService orders;
    private final Logger logger = Logger.getLogger(MarketFacade.class.getName());

    public MarketFacade(InventoryService inv, PaymentService pay, OrderService ord) {
        this.inventory = Objects.requireNonNull(inv);
        this.payment = Objects.requireNonNull(pay);
        this.orders = Objects.requireNonNull(ord);
    }

    public boolean placeOrder(String productCode, int qty, String accountId, double amount) {
        if (!inventory.reserve(productCode, qty)) {
            logger.warning("Cannot reserve product " + productCode);
            return false;
        }
        if (!payment.charge(accountId, amount)) {
            logger.warning("Payment failed for " + accountId);
            return false;
        }
        String orderId = orders.createOrder(productCode, qty);
        logger.info("Order placed successfully: " + orderId);
        return true;
    }
}
