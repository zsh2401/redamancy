package org.redamancy.server;

import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.redamancy.server.exception.RestfulException;
import org.redamancy.server.style.restful.Restful;
import org.redamancy.server.style.restful.RestfulHandler;
import org.redamancy.server.style.restful.RestfulResponse;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletResponse;

/**
 * @author zsh2401
 * @program serein
 * @create 2020-12-06 21:34
 **/
@Aspect
@Component
@Slf4j
public class AOPHandlers {

    @Around(value = "@annotation(restful)")
    public final Object restfulResultHandler(ProceedingJoinPoint pjp, Restful restful) throws Throwable {
        return RestfulHandler.restfulResultHandler(pjp, restful);
    }
}
