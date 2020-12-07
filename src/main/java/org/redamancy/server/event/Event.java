package org.redamancy.server.event;

import lombok.Getter;
import org.jetbrains.annotations.Nullable;

/**
 * All event's base class in serein system.
 */
@Getter
public abstract class Event {

    @Getter
    private final long raiseTime;

    @Getter
    @Nullable
    private final Object source;

    public Event(@Nullable Object sender) {
        this.source = sender;
        this.raiseTime = now();
    }

    public Event() {
        this(null);
    }

    private static long now() {
        return System.currentTimeMillis();
    }
}
