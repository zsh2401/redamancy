package org.redamancy.server.util;

import com.sun.management.OperatingSystemMXBean;
import lombok.AccessLevel;
import lombok.Data;
import lombok.Setter;
import org.redamancy.server.App;
import org.redamancy.server.service.IRedamancyConfiguration;

import java.lang.management.ManagementFactory;

/**
 * @author zsh2401
 * @program redamancy
 * @create 2020-12-07 16:30
 **/
@Data
public class SystemInfo {

    private final String appName;
    private final String version;
    private final long usedMemory;
    private final long freeMemory;
    private final long totalRam;
    private final long timestamp;
    private final String jvmVersion;
    private final String osName;
    private final int cpu;
    private final double cpuUsage;

    private static final IRedamancyConfiguration configuration = App.getContext().getBean(IRedamancyConfiguration.class);
    private static final OperatingSystemMXBean osmxb = (OperatingSystemMXBean) ManagementFactory.getOperatingSystemMXBean();

    public SystemInfo() {
        appName = configuration.getAppName();
        version = configuration.getVersion();
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
