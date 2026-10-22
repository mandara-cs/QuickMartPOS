package com.quickmart.util;

import java.io.File;
import java.util.logging.FileHandler;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.logging.SimpleFormatter;

public class AppLogger {

    private static final Logger LOGGER = Logger.getLogger("QuickMartPOS");

    static {
        try {
            File logDir = new File("logs");
            if (!logDir.exists()) {
                logDir.mkdirs();
            }
            FileHandler fileHandler = new FileHandler("logs/quickmart_app.log", true);
            fileHandler.setFormatter(new SimpleFormatter());
            LOGGER.addHandler(fileHandler);
            LOGGER.setUseParentHandlers(true);
        } catch (Exception e) {
            System.err.println("Failed to initialize file logger: " + e.getMessage());
        }
    }

    public static void info(String message) {
        LOGGER.log(Level.INFO, message);
    }

    public static void error(String message, Throwable throwable) {
        LOGGER.log(Level.SEVERE, message, throwable);
    }
}