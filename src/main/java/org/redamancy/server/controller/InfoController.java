package org.redamancy.server.controller;

import com.sun.management.OperatingSystemMXBean;
import lombok.AccessLevel;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;
import org.redamancy.server.exception.RestfulException;
import org.redamancy.server.service.IRedamancyConfiguration;
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

    @GetMapping
    @OptionalFeature("info-api")
    public Object all() throws RestfulException, IOException {
        return new SystemInfo();
    }

    @Data
    @Setter(AccessLevel.NONE)
    class SystemInfo {
        private String appName = configuration.getAppName();
        private String version = "0.0.1";
        private long usedMemory;
        private long freeMemory;
        private long totalRam;
        private long timestamp;
        private String jvmVersion;
        private String osName;
        private int cpu;
        private double cpuUsage;

        public SystemInfo() {
            OperatingSystemMXBean osmxb = (OperatingSystemMXBean) ManagementFactory.getOperatingSystemMXBean();

            totalRam = (osmxb.getTotalPhysicalMemorySize() / 1024 / 1024);
            freeMemory = (osmxb.getFreePhysicalMemorySize() / 1024 / 1024);
            usedMemory = (getTotalRam() - getFreeMemory());
            timestamp = System.currentTimeMillis();
            jvmVersion = System.getProperty("java.version");
            osName = System.getProperty("os.name");
            cpu = Runtime.getRuntime().availableProcessors();
            cpuUsage = osmxb.getProcessCpuLoad();
        }
    }
}
