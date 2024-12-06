package com.alucontrol.saleservice.tracking;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

// Centralizes log methods for different levels (info, warn, error)
// and helps standardize how these logs are recorded across the application.
public class LogUtil {

    private static final Logger logger = LoggerFactory.getLogger(LogUtil.class);

    public static void info(String message) {
        logger.info("{}", message);
    }

    public static void info(String message, Throwable throwable) {
        logger.info("{}", message, throwable);
    }

    public static void error(String message) {
        logger.error("{}", message);
    }

    public static void error(String message, Throwable throwable) {
        logger.error("{}", message, throwable);
    }

    //Specific logs for database operations
    public static void logDatabaseOperation(String message) {
        logger.info("[Database] {}", message);
    }

    //Specific logs for requests to other microservices
    public static void logServiceRequest(String message) {
        logger.info("[ServiceRequest] {}", message);
    }
}
