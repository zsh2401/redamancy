package org.redamancy.server.service.impl;

import org.redamancy.server.service.ISystemService;
import org.redamancy.server.util.SystemInfo;
import org.springframework.stereotype.Component;

/**
 * @author zsh2401
 * @program redamancy
 * @create 2020-12-07 16:32
 **/
@Component
public class SystemServiceImpl implements ISystemService {
    @Override
    public SystemInfo nowSystemInfo() {
        return new SystemInfo();
    }
}
