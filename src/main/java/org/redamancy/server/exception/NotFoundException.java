package org.redamancy.server.exception;

/**
 * @author zsh2401
 * @program serein
 * @create 2020-12-07 00:03
 **/
public final class NotFoundException extends RestfulException {
    public NotFoundException() {
        super(404, null, null);
    }
}
