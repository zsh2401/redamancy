package org.redamancy.server.style.restful;

import lombok.Data;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import org.redamancy.server.exception.RestfulException;

@Data
public class RestfulResponse<D> {

    public static final int DEFAULT_OK_CODE = 200;

    public static final int DEFAULT_ERROR_CODE = 500;

    private final int statusCode;

    @Nullable
    private final D data;

    @Nullable
    private final String message;

    public RestfulResponse(int statusCode, @Nullable D data, @Nullable String message) {
        this.statusCode = statusCode;
        this.data = data;
        this.message = message;
    }

    public static <R> RestfulResponse<R> ok() {
        return new RestfulResponse<R>(DEFAULT_OK_CODE, null, null);
    }

    public static <R> RestfulResponse<R> ok(R result) {
        return new RestfulResponse<R>(DEFAULT_OK_CODE, result, null);
    }

    public static <R> RestfulResponse error(int statusCode, @NotNull Exception cause) {
        return new RestfulResponse<R>(statusCode, null, cause.toString());
    }

    public static <R> RestfulResponse error(int statusCode, @NotNull String message) {
        return new RestfulResponse<R>(statusCode, null, message);
    }

    public static <R> RestfulResponse error(@Nullable String message) {
        return new RestfulResponse<R>(DEFAULT_ERROR_CODE, null, message);
    }

    public static <R> RestfulResponse error(int statusCode) {
        return new RestfulResponse<R>(statusCode, null, null);
    }

    public static <R> RestfulResponse error(RestfulException e) {
        return new RestfulResponse<R>(e.getStatusCode(), null, e.getMessage());
    }

    public static <R> RestfulResponse error() {
        return new RestfulResponse<R>(500, null, null);
    }


    public static <R> RestfulResponse<R> run(ResultProvider<R> provider) {
        try {
            R result = provider.run();
            return ok(result);
        } catch (RestfulException re) {
            return error(re);
        } catch (Exception e) {
            return error(DEFAULT_ERROR_CODE, e);
        }
    }

    @FunctionalInterface
    public interface ResultProvider<R> {
        R run() throws Exception;
    }
}
