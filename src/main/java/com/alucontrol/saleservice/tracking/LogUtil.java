package com.alucontrol.saleservice.tracking;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.slf4j.MDC;

// Centralizes log methods for different levels (info, warn, error)
// and helps standardize how these logs are recorded across the application.
public class LogUtil {

    private static final Logger logger = LoggerFactory.getLogger(LogUtil.class);


    // Método para logar o traceId
    public static void logTraceId() {
        String traceId = MDC.get("X-B3-TraceId");
        logger.info("Trace ID: {}", traceId);
    }


//
//    public static void info(String message) {
//        logger.info("[{}] {}", MDC.get("requestId"), message);
//    }
//
//    public static void info(String message, Throwable throwable) {
//        logger.info("[{}] {}", MDC.get("requestId"), message, throwable);
//    }
//
//    public static void error(String message) {
//        logger.error("[{}] {}", MDC.get("requestId"), message);
//    }
//
//    public static void error(String message, Throwable throwable) {
//        logger.error("[{}] {}", MDC.get("requestId"), message, throwable);
//    }
//
//    //Specific logs for database operations
//    public static void logDatabaseOperation(String message) {
//        logger.info("[{}] [Database] {}", MDC.get("requestId"), message);
//    }
//
//    //Specific logs for requests to other microservices
//    public static void logServiceRequest(String message) {
//        logger.info("[{}] [ServiceRequest] {}", MDC.get("requestId"), message);
//    }
}
