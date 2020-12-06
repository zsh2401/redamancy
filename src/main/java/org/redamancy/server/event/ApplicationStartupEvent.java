package org.redamancy.server.event;

import org.springframework.context.ApplicationContext;

public final class ApplicationStartupEvent extends Event {

    private final ApplicationContext ctx;

    public ApplicationStartupEvent(ApplicationContext ctx) {
        super(ctx);
        this.ctx = ctx;
    }

    public ApplicationContext getContext() {
        return ctx;
    }
}
