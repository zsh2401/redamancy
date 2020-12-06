package org.redamancy.server.style.restful;

import org.aspectj.lang.ProceedingJoinPoint;
import org.redamancy.server.exception.RestfulException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.servlet.http.HttpServletResponse;

/**
 * @author zsh2401
 * @program serein
 * @create 2020-12-06 21:33
 **/
public class RestfulHandler {
    private static Logger logger = LoggerFactory.getLogger(RestfulHandler.class);

    public static Object restfulResultHandler(ProceedingJoinPoint pjp, Restful restful) throws Throwable {
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
            return RestfulResponse.error(HttpServletResponse.SC_INTERNAL_SERVER_ERROR, exception);
        } catch (Throwable throwable) {
            logger.error("api fatal", throwable);
            throw throwable;
        }
    }
}
