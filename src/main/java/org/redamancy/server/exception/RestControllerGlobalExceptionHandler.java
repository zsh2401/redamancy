package org.redamancy.server.exception;

import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.annotation.Around;
import org.redamancy.server.style.restful.RestfulResponse;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.WebRequest;

/**
 * @author zsh2401
 * @program redamancy
 * @create 2020-12-07 14:22
 **/
@RestControllerAdvice(annotations = RestController.class)
@Slf4j
public class RestControllerGlobalExceptionHandler {

    @ExceptionHandler(Exception.class)
    @ResponseBody
    public final ResponseEntity handleException(Exception ex, WebRequest request) {
        final int statusCode = HttpStatus.INTERNAL_SERVER_ERROR.value();
        log.error("", ex);
        return ResponseEntity.status(statusCode)
                .body(RestfulResponse.error(statusCode, ex.getCause()));
    }

    @ExceptionHandler(RestfulException.class)
    @ResponseBody
    public final ResponseEntity handleRestfulException(RestfulException exception, WebRequest request) {
        final int statusCode = exception.getStatusCode();
        return ResponseEntity.status(statusCode)
                .body(RestfulResponse.error(exception));
    }
}
