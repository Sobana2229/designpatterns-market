package com.designpatterns.market.config;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;
import java.util.logging.Logger;

public final class Config {
    private static final String CONFIG_FILE = "/config.properties";
    private static final Properties props = new Properties();
    private static final Logger LOGGER = java.util.logging.Logger.getLogger("Config");

    static {
        try (InputStream is = Config.class.getResourceAsStream(CONFIG_FILE)) {
            if (is == null) {
                LOGGER.warning("config.properties not found in resources; using defaults.");
            } else {
                props.load(is);
            }
        } catch (IOException e) {
            LOGGER.warning("Failed to load config: " + e.getMessage());
        }
    }

    private Config(){}

    public static int getInt(String key, int defaultValue) {
        String v = props.getProperty(key);
        if (v == null) return defaultValue;
        try { return Integer.parseInt(v.trim()); }
        catch (NumberFormatException ex) {
            LOGGER.warning("Invalid integer for key " + key + ", using default " + defaultValue);
            return defaultValue;
        }
    }
}
