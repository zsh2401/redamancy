package org.redamancy.server;

import lombok.Getter;
import org.greenrobot.eventbus.EventBus;
import org.redamancy.server.event.ApplicationStartupEvent;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ConfigurableApplicationContext;

/**
 * Application.
 */
@SpringBootApplication
public class App {
    @Getter
    private static ApplicationContext context;

    @Deprecated
    public static ApplicationContext current() {
        return context;
    }

    public static void main(String[] args) {
        //Run this spring application.
        beforeApplicationStartup();
        ConfigurableApplicationContext context = SpringApplication.run(App.class, args);
        afterApplicationStartup(context);
        //Idle until process exited.
    }

    private static void beforeApplicationStartup() {
        //TODO
    }

    private static void afterApplicationStartup(ConfigurableApplicationContext ctx) {
        context = ctx;
        ctx.getBean(EventBus.class).postSticky(new ApplicationStartupEvent(ctx));
    }
}
