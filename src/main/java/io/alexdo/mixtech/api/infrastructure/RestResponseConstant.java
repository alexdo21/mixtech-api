package io.alexdo.mixtech.api.infrastructure;

public class RestResponseConstant {
    // Status
    public static final String SUCCESS = "Success";
    public static final String FAILURE = "Failure";

    // Description
    public static <T> String DESCRIPTION(Class<T> cls, String endpoint) {
        return String.format("%s requested at: %s", cls.getSimpleName(), endpoint);
    }

    // Error Message
    public static <T> String ERROR(Class<T> exceptionClass, String exceptionMessage) {
        return String.format("Exception: %s occurred with message: %s", exceptionClass.getSimpleName(), exceptionMessage);
    }
}
