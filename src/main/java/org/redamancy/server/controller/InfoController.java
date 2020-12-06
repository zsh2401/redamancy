package org.redamancy.server.controller;

import com.sun.management.OperatingSystemMXBean;
import lombok.AccessLevel;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;
import org.redamancy.server.exception.RestfulException;
import org.redamancy.server.style.restful.Restful;
import org.redamancy.server.style.restful.RestfulResponse;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.lang.management.ManagementFactory;

/**
 * @author zsh2401
 * @program redamancy
 * @create 2020-12-07 00:10
 **/
@RestController
@RequestMapping("info")
public class InfoController {
    @Value("${redamancy.name}")
    private String appName;

    @Value("${redamancy.features.info-api}")
    private boolean enabled;

    @GetMapping
    @Restful
    public Object all() throws RestfulException {
        if (enabled) {
            return new SystemInfo();
        } else {
            throw RestfulException.notAcceptable("feature disabled");
        }
    }

    @Data
    @Setter(AccessLevel.NONE)
    class SystemInfo {
        private String appName = getAppName();
        private String version = "0.0.1";
        private long usedMemory;
        private long freeMemory;
        private long totalRam;
        private long timestamp;

        public SystemInfo() {
            OperatingSystemMXBean mem = (OperatingSystemMXBean) ManagementFactory.getOperatingSystemMXBean();
            totalRam = (mem.getTotalPhysicalMemorySize() / 1024 / 1024);
            freeMemory = (mem.getFreePhysicalMemorySize() / 1024 / 1024);
            usedMemory = (getTotalRam() - getFreeMemory());
            timestamp = System.currentTimeMillis();
        }
    }
}
