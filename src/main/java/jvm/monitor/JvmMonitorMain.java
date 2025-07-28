package jvm.monitor;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.lang.management.*;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

public class JvmMonitorMain {
    public static void main(String[] args) throws JsonProcessingException {
        Map<String, Object> metrics = new LinkedHashMap<>();

        MemoryMXBean memory = ManagementFactory.getMemoryMXBean();
        ThreadMXBean threads = ManagementFactory.getThreadMXBean();
        ClassLoadingMXBean classes = ManagementFactory.getClassLoadingMXBean();
        OperatingSystemMXBean os = ManagementFactory.getOperatingSystemMXBean();
        List<GarbageCollectorMXBean> gcs = ManagementFactory.getGarbageCollectorMXBeans();

        metrics.put("jvm.memory.used", memory.getHeapMemoryUsage().getUsed());
        metrics.put("jvm.memory.max", memory.getHeapMemoryUsage().getMax());
        metrics.put("jvm.thread.count", threads.getThreadCount());
        metrics.put("jvm.classes.loaded", classes.getLoadedClassCount());
        metrics.put("jvm.uptime", ManagementFactory.getRuntimeMXBean().getUptime());

        long gcCount = 0, gcTime = 0;
        for (GarbageCollectorMXBean gc : gcs) {
            gcCount += gc.getCollectionCount();
            gcTime += gc.getCollectionTime();
        }
        metrics.put("jvm.gc.count", gcCount);
        metrics.put("jvm.gc.pause", gcTime);

        ObjectMapper mapper = new ObjectMapper();
        System.out.println(mapper.writerWithDefaultPrettyPrinter().writeValueAsString(metrics));
    }
}
