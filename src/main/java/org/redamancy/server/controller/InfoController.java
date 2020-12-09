package org.redamancy.server.controller;

import com.sun.management.OperatingSystemMXBean;
import lombok.AccessLevel;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;
import org.redamancy.server.exception.RestfulException;
import org.redamancy.server.service.IRedamancyConfiguration;
import org.redamancy.server.service.ISystemService;
import org.redamancy.server.style.condition.OptionalFeature;
import org.redamancy.server.style.restful.RestfulResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;
import java.lang.management.ManagementFactory;

/**
 * @author zsh2401
 * @program redamancy
 * @create 2020-12-07 00:10
 **/
@RestController
@RequestMapping("info")
public class InfoController {

    @Autowired
    @Getter
    private IRedamancyConfiguration configuration;

    @Autowired
    @Getter
    private ISystemService systemService;

    @GetMapping
    @OptionalFeature("info-api")
    public Object all() throws RestfulException, IOException {
        return systemService.nowSystemInfo();
    }
}
