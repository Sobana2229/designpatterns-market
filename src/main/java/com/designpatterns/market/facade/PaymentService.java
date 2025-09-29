package com.designpatterns.market.facade;

import com.designpatterns.market.adapter.PaymentProcessor;
import java.util.logging.Logger;

public class PaymentService {
    private final Logger logger = Logger.getLogger(PaymentService.class.getName());
    private final PaymentProcessor processor;

    public PaymentService(PaymentProcessor processor) {
        this.processor = processor;
    }

    public boolean charge(String accountId, double amount) {
        logger.info("Charging " + accountId + " => " + amount);
        return processor.charge(accountId, amount);
    }
}
