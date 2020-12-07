package org.redamancy.server.service.impl;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.Setter;
import org.redamancy.server.App;
import org.redamancy.server.service.IRedamancyConfiguration;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Map;

/**
 * @author zsh2401
 * @program redamancy
 * @create 2020-12-07 12:42
 **/
@Component
@ConfigurationProperties(prefix = "redamancy")
public class RedamancyConfiguration implements IRedamancyConfiguration {

    @Getter
    @Setter
    private String appName;

    @Getter
    @Setter
    private String version;

    @Getter(AccessLevel.PRIVATE)
    @Setter
    private List<String> features;

    @Override
    public boolean isFeatureEnabled(String key) {
        return getFeatures().contains(key);
    }
}
