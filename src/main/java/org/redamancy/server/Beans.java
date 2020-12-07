package org.redamancy.server;

import org.greenrobot.eventbus.EventBus;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

import java.util.concurrent.*;

/**
 * The service or component's factory.
 * Should be instantiated by Spring Framework.
 *
 * @author zsh2401
 * @
 */
@Configuration
public class Beans {

    @Bean
    public EventBus eventBus() {
        return new EventBus();
    }
}
