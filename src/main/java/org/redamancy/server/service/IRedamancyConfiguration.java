package org.redamancy.server.service;

import java.util.Map;

/**
 * @author zsh2401
 * @program redamancy
 * @create 2020-12-07 12:37
 **/
public interface IRedamancyConfiguration {

    String getAppName();

    String getVersion();

    boolean isFeatureEnabled(String key);
}
