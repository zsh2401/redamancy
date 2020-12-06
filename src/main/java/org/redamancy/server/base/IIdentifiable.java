package org.redamancy.server.base;

import java.io.Serializable;

public interface IIdentifiable<ID extends Serializable> {
    ID getId();
}
