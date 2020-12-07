package org.redamancy.server.style.restful;

import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.ProceedingJoinPoint;
import org.redamancy.server.exception.RestfulException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import javax.servlet.http.HttpServletResponse;

/**
 * @author zsh2401
 * @program serein
 * @create 2020-12-06 21:33
 **/
@Slf4j
public class RestfulHandler {
    private RestfulHandler() {
    }

    public static Object handle(ProceedingJoinPoint pjp, Restful restful) throws Throwable {
        Logger logger = log;
        try {
            Object result = pjp.proceed();
            if (result instanceof RestfulResponse) {
                return result;
            }
            return RestfulResponse.ok(result);
        } catch (RestfulException re) {
            logger.warn("restful api exception", re);
            return RestfulResponse.error(re);
        } catch (Exception exception) {
            logger.warn("unknown api exception", exception);
            return RestfulResponse.error(HttpStatus.INTERNAL_SERVER_ERROR.value(), exception);
        } catch (Throwable throwable) {
            logger.error("api fatal", throwable);
            throw throwable;
        }
    }
}
