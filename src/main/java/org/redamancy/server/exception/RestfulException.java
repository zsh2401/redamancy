package org.redamancy.server.exception;

import lombok.Data;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import javax.servlet.http.HttpServletResponse;

@Data
public class RestfulException extends Exception {

    @lombok.Getter
    private final int statusCode;

    public RestfulException(@NotNull int statusCode, @Nullable String message, @Nullable Exception cause) {
        super(message, cause);
        this.statusCode = statusCode;
    }

    public RestfulException(@Nullable String message, @Nullable Exception cause) {
        this(HttpServletResponse.SC_INTERNAL_SERVER_ERROR, message, cause);
    }

    public RestfulException(@Nullable String message) {
        this(HttpServletResponse.SC_INTERNAL_SERVER_ERROR, message, null);
    }

    public RestfulException() {
        this(HttpServletResponse.SC_INTERNAL_SERVER_ERROR, DEFAULT_INTERNAL_SERVER_ERROR_MESSAGE, null);
    }


    private static final String DEFAULT_NOT_FOUND_MESSAGE = "No such element.";
    private static final String DEFAULT_INTERNAL_SERVER_ERROR_MESSAGE = "Internal server error";
    private static final String DEFAULT_NOT_ACCEPTABLE = "Not acceptable";

    public static RestfulException notFound(@Nullable String message) {
        return auto(HttpServletResponse.SC_NOT_FOUND, message, DEFAULT_NOT_FOUND_MESSAGE);
    }

    public static RestfulException internalServerError(@Nullable String message) {
        return auto(HttpServletResponse.SC_INTERNAL_SERVER_ERROR, message, DEFAULT_INTERNAL_SERVER_ERROR_MESSAGE);
    }

    public static RestfulException notAcceptable(@Nullable String message) {
        return auto(HttpServletResponse.SC_NOT_ACCEPTABLE, message, DEFAULT_NOT_ACCEPTABLE);
    }

    private static RestfulException auto(int statusCode, @Nullable String message, @NotNull String defaultMessage) {
        if (message == null) {
            message = defaultMessage;
        }
        return new RestfulException(statusCode, message, null);
    }

}
