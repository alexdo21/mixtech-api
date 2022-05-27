package io.alexdo.mixtech.application.logging;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class Logger {
    public static void logInfo(String message, Object source) {
        log.info(String.format("%s: %s", source.getClass().getSimpleName(), message));
    }

    public static void logWarn(String message, Object source) {
        log.warn(String.format("%s: %s", source.getClass().getSimpleName(), message));
    }

    public static void logError(String message, Object source) {
        log.error(String.format("%s: %s", source.getClass().getSimpleName(), message));
    }
}
