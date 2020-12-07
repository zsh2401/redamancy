package org.redamancy.server.exception;

import org.springframework.http.HttpStatus;

/**
 * @author zsh2401
 * @program serein
 * @create 2020-12-07 00:03
 **/
public final class NotFoundException extends RestfulException {
    public NotFoundException() {
        super(HttpStatus.NOT_FOUND.value(), null, null);
    }
}
