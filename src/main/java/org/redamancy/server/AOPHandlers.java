package org.redamancy.server;

import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.redamancy.server.style.condition.OptionalFeature;
import org.redamancy.server.style.condition.OptionalFeatureHandler;
import org.redamancy.server.style.restful.Restful;
import org.redamancy.server.style.restful.RestfulHandler;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * @author zsh2401
 * @program serein
 * @create 2020-12-06 21:34
 **/
@Aspect
@Component
@Slf4j
public class AOPHandlers {

    @Around("@annotation(restful)")
    @Order(0)
    public final Object restfulResultHandler(ProceedingJoinPoint pjp, Restful restful) throws Throwable {
        return RestfulHandler.handle(pjp, restful);
    }

    @Around("@annotation(optionalfeature)")
    @Order(Integer.MAX_VALUE)
    public final Object featureHandler(ProceedingJoinPoint pjp, OptionalFeature optionalfeature) throws Throwable {
        return OptionalFeatureHandler.handle(pjp, optionalfeature);
    }

    @Around("execution(* *.*Controller.*(..))")
    public final Object requestMapping(ProceedingJoinPoint pjp) throws Throwable {
        return pjp.proceed();
    }
}
