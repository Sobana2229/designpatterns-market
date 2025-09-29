package com.designpatterns.market;

import com.designpatterns.market.logging.LoggerFactory;
import com.designpatterns.market.config.Config;
import com.designpatterns.market.observer.*;
import com.designpatterns.market.strategy.*;
import com.designpatterns.market.factory.*;
import com.designpatterns.market.builder.*;
import com.designpatterns.market.adapter.*;
import com.designpatterns.market.facade.*;

import java.util.concurrent.*;
import java.util.logging.Logger;
import java.util.Random;

/**
 * App demonstrates six design pattern use-cases.
 * - Observer: MarketNews + Subscribers
 * - Strategy: Discount strategies applied via DiscountContext
 * - Factory Method: TransportFactory chooses Truck or Bike
 * - Builder: ProductBuilder constructs complex Product
 * - Adapter: LegacyPaymentAdapter adapts old gateway
 * - Facade: MarketFacade orchestrates order placement
 *
 * The app runs a short simulation (configurable via config.properties).
 */
public class App {
    private static final Logger LOGGER = LoggerFactory.getLogger();
    public static void main(String[] args) {
        LOGGER.info("=== Design Patterns Market Demo ===");

        // 1) Observer pattern
        MarketNews market = new MarketNews();
        Subscriber alice = new BuyerSubscriber("Alice"); 
        Subscriber bob = new FarmerSubscriber("BobFarm");
        market.subscribe(alice);
        market.subscribe(bob);

        // 2) Strategy pattern
        DiscountContext discount = new DiscountContext(new NoDiscount());
        LOGGER.info("Using strategy: " + discount.currentStrategyName() + ", price: " + discount.calculate(100));
        discount.setStrategy(new SeasonalDiscount(10));
        LOGGER.info("Using strategy: " + discount.currentStrategyName() + ", price: " + discount.calculate(100));
        discount.setStrategy(new BulkDiscount(10, 20));
        LOGGER.info("Using strategy: " + discount.currentStrategyName() + ", price: " + discount.calculate(100));

        // 3) Factory Method
        Transport t = TransportFactory.create(TransportFactory.TransportType.BIKE);
        t.deliver("Apples crate");

        // 4) Builder
        Product p = new ProductBuilder().setName("Wheat").setCode("WH-01").setCredits(3).setPrice(250).setOrigin("LocalFarm").build();
        LOGGER.info("Built product: " + p);

        // 5) Adapter + Facade integration demonstration prepared below
        LegacyPaymentGateway legacy = new LegacyPaymentGateway();
        PaymentProcessor adapted = new LegacyPaymentAdapter(legacy);
        PaymentService paymentService = new PaymentService(adapted);
        InventoryService inventoryService = new InventoryService();
        OrderService orderService = new OrderService();
        MarketFacade facade = new MarketFacade(inventoryService, paymentService, orderService);

        boolean placed = facade.placeOrder(p.getCode(), 1, "acct-123", p.getPrice());
        LOGGER.info("Order placement result: " + placed);

        // --- Long-running simulation of market updates (without while(true)) ---
        int interval = Config.getInt("simulation.interval.seconds", 5);
        int duration = Config.getInt("simulation.run.duration.seconds", 20);
        ScheduledExecutorService scheduler = Executors.newScheduledThreadPool(2);

        final Random rnd = new Random();
        final String[] items = new String[]{"Wheat", "Rice", "Tomato", "Potato"};

        Runnable publishTask = () -> {
            String item = items[rnd.nextInt(items.length)];
            double price = 10 + rnd.nextDouble() * 300;
            try {
                market.publishPrice(item, Math.round(price * 100.0) / 100.0);
            } catch (Exception ex) {
                LOGGER.warning("Publish failed: " + ex.getMessage());
            }
        };

        LOGGER.info("Starting market simulation: interval=" + interval + "s, duration=" + duration + "s");
        ScheduledFuture<?> future = scheduler.scheduleAtFixedRate(publishTask, 0, interval, TimeUnit.SECONDS);

        // stop after duration seconds (defensive - avoid infinite loop)
        scheduler.schedule(() -> {
            future.cancel(false);
            scheduler.shutdown();
            LOGGER.info("Simulation finished. Shutting down."); 
        }, duration, TimeUnit.SECONDS);

        // Wait for termination in a defensive way
        try {
            if (!scheduler.awaitTermination(duration + 5, TimeUnit.SECONDS)) {
                LOGGER.warning("Scheduler did not terminate within expected time; forcing shutdown");
                scheduler.shutdownNow();
            }
        } catch (InterruptedException ie) {
            Thread.currentThread().interrupt();
            LOGGER.warning("Main thread interrupted: " + ie.getMessage());
        }

        LOGGER.info("=== Demo Completed ===");
    }
}
