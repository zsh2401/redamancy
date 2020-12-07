package org.redamancy.server.exception;

/**
 * @author zsh2401
 * @program redamancy
 * @create 2020-12-07 14:14
 **/
public final class DisabledFeatureException extends Exception {
    public DisabledFeatureException(String featureName){
        super(featureName + " has not been enabled.");
    }
}
