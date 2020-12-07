package org.redamancy.server.exception;

import org.springframework.http.HttpStatus;

/**
 * @author zsh2401
 * @program redamancy
 * @create 2020-12-07 14:14
 **/
public final class DisabledFeatureException extends RestfulException {
    public DisabledFeatureException(String featureName) {
        super(HttpStatus.NOT_ACCEPTABLE.value(), "the feature <" + featureName + "> has not been enabled.", null);
    }
}
