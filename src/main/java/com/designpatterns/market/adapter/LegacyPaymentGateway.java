package com.designpatterns.market.adapter;

/**
 * Simulates a legacy payment gateway with a different interface.
 */
public class LegacyPaymentGateway {
    public String sendPayment(String account, double amountCents) {
        // old API expects amount in cents as double for historic reasons
        // returns a status string
        if (account == null || account.isEmpty()) return "ERROR:invalid_account";
        if (amountCents <= 0) return "ERROR:invalid_amount";
        // simulate success
        return "OK:txid:" + System.currentTimeMillis();
    }
}
