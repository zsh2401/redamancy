package org.redamancy.server.style;

import lombok.extern.slf4j.Slf4j;
import org.redamancy.server.exception.RestfulException;
import org.redamancy.server.style.restful.RestfulResponse;
import org.springframework.core.MethodParameter;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.http.server.ServerHttpRequest;
import org.springframework.http.server.ServerHttpResponse;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseBodyAdvice;

import java.lang.reflect.Method;

/**
 * @author zsh2401
 * @program redamancy
 * @create 2020-12-07 14:22
 **/
@RestControllerAdvice(annotations = RestController.class)
@Slf4j
public class StdRestControllerAdvice implements ResponseBodyAdvice {

    @ExceptionHandler(Exception.class)
    @ResponseBody
    public final ResponseEntity handleException(Exception ex, WebRequest request) {
        final int statusCode = HttpStatus.INTERNAL_SERVER_ERROR.value();
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

    @Override
    public boolean supports(MethodParameter returnType, Class converterType) {
        return true;
    }

    @Override
    public Object beforeBodyWrite(Object body, MethodParameter returnType,
                                  MediaType selectedContentType, Class selectedConverterType,
                                  ServerHttpRequest request, ServerHttpResponse response) {
        RestfulResponse restfulResponse;
        if (body instanceof RestfulResponse) {
            restfulResponse = ((RestfulResponse) body);
        } else {
            restfulResponse = RestfulResponse.ok(body);
        }
        int statusCode = restfulResponse.getStatusCode();
        response.setStatusCode(HttpStatus.valueOf(statusCode));
        return restfulResponse;
    }
}
