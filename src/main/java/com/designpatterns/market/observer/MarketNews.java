package com.designpatterns.market.observer;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.logging.Logger;

public class MarketNews {
    private final List<Subscriber> subscribers = new ArrayList<>();
    private final Logger logger = Logger.getLogger(MarketNews.class.getName());

    public void subscribe(Subscriber s) {
        Objects.requireNonNull(s, "Subscriber cannot be null");
        synchronized (subscribers) {
            if (!subscribers.contains(s)) {
                subscribers.add(s);
                logger.info("Subscriber added: " + s.getName());
            }
        }
    }

    public void unsubscribe(Subscriber s) {
        Objects.requireNonNull(s, "Subscriber cannot be null");
        synchronized (subscribers) {
            subscribers.remove(s);
            logger.info("Subscriber removed: " + s.getName());
        }
    }

    public void publishPrice(String item, double price) {
        logger.info("Publishing price update: " + item + " => " + price);
        List<Subscriber> snapshot;
        synchronized (subscribers) {
            snapshot = new ArrayList<>(subscribers);
        }
        for (Subscriber s : snapshot) {
            try {
                s.update(item, price);
            } catch (Exception ex) {
                logger.warning("Failed to notify " + s.getName() + ": " + ex.getMessage());
            }
        }
    }
}
