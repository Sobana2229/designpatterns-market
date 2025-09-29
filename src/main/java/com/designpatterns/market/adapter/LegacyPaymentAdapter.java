package com.designpatterns.market.adapter;

import java.util.logging.Logger;

/**
 * Adapter converts the old gateway responses to the new boolean semantics.
 */
public class LegacyPaymentAdapter implements PaymentProcessor {
    private final LegacyPaymentGateway legacy;
    private final Logger logger = Logger.getLogger(LegacyPaymentAdapter.class.getName());

    public LegacyPaymentAdapter(LegacyPaymentGateway legacy) {
        this.legacy = legacy;
    }

    @Override
    public boolean charge(String accountId, double amount) {
        if (accountId == null || accountId.isEmpty()) throw new IllegalArgumentException("accountId required");
        if (amount <= 0) throw new IllegalArgumentException("amount must be > 0");
        // legacy expects cents as double
        double cents = amount * 100.0;
        String resp = legacy.sendPayment(accountId, cents);
        logger.info("Legacy response: " + resp);
        return resp != null && resp.startsWith("OK:");
    }
}
