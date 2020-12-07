package org.redamancy.server.style.condition;

import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.ProceedingJoinPoint;
import org.redamancy.server.App;
import org.redamancy.server.exception.DisabledFeatureException;
import org.redamancy.server.exception.RestfulException;
import org.redamancy.server.service.IRedamancyConfiguration;
import org.redamancy.server.style.restful.RestfulResponse;
import org.slf4j.Logger;

import javax.servlet.http.HttpServletResponse;

/**
 * @author zsh2401
 * @program redamancy
 * @create 2020-12-07 13:03
 **/
@Slf4j
public class OptionalFeatureHandler {
    private OptionalFeatureHandler() {
    }

    public static Object handle(ProceedingJoinPoint pjp, OptionalFeature optionalFeature) throws Throwable {
        Logger logger = log;
        IRedamancyConfiguration redamancyConfiguration = App.getContext().getBean(IRedamancyConfiguration.class);
        if (redamancyConfiguration.isFeatureEnabled(optionalFeature.value())) {
            return pjp.proceed();
        } else {
            throw new DisabledFeatureException(optionalFeature.value());
        }
    }
}
