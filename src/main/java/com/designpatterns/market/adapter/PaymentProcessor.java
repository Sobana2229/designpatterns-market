package com.designpatterns.market.adapter;

/**
 * Current system expects this interface for payments.
 */
public interface PaymentProcessor {
    boolean charge(String accountId, double amount);
}
