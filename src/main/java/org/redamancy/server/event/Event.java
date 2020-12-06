package org.redamancy.server.event;

import lombok.Data;
import lombok.Getter;
import lombok.ToString;
import org.jetbrains.annotations.Nullable;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletResponse;

/**
 * All event's base class in serein system.
 */
@Getter
public abstract class Event {

    @Getter
    private final long raiseTime;

    @Getter
    @Nullable
    private final Object sender;

    public Event(@Nullable Object sender) {
        this.sender = sender;
        this.raiseTime = now();
    }

    public Event() {
        this(null);
    }

    private static long now() {
        return System.currentTimeMillis();
    }
}
