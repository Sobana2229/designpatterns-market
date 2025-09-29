package com.designpatterns.market.logging;

import java.io.IOException;
import java.util.logging.*;

public class LoggerFactory {
    private static final Logger LOGGER = Logger.getLogger("DesignPatternsMarket");

    static {
        try {
            LOGGER.setLevel(Level.INFO);
            ConsoleHandler ch = new ConsoleHandler();
            ch.setLevel(Level.INFO);
            LOGGER.addHandler(ch);
            // optional file handler
            Handler fh = new FileHandler("designpatterns-market.log", true);
            fh.setFormatter(new SimpleFormatter());
            fh.setLevel(Level.INFO);
            LOGGER.addHandler(fh);
            // avoid duplicate logs
            LOGGER.setUseParentHandlers(false);
        } catch (IOException e) {
            System.err.println("Failed to initialize logger: " + e.getMessage());
        }
    }

    private LoggerFactory() {}

    public static Logger getLogger() {
        return LOGGER;
    }
}
