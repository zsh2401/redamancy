package org.redamancy.server;

import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.redamancy.server.style.condition.OptionalFeature;
import org.redamancy.server.style.condition.OptionalFeatureHandler;
import org.redamancy.server.style.restful.RestfulResult;
import org.redamancy.server.style.restful.RestfulResultHandler;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

/**
 * @author zsh2401
 * @program serein
 * @create 2020-12-06 21:34
 **/
@Aspect
@Component
@Slf4j
public class AOPHandlers {

    @Around("@annotation(optionalfeature)")
    public final Object featureHandler(ProceedingJoinPoint pjp, OptionalFeature optionalfeature) throws Throwable {
        return OptionalFeatureHandler.handle(pjp, optionalfeature);
    }

}
