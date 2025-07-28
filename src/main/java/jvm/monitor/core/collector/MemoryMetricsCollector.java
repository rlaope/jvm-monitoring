package jvm.monitor.core.collector;

import jvm.monitor.core.MetricsCollector;

import java.lang.management.ManagementFactory;
import java.lang.management.MemoryMXBean;
import java.util.HashMap;
import java.util.Map;

public class MemoryMetricsCollector implements MetricsCollector {
    @Override
    public String domain() {
        return "memory";
    }

    @Override
    public Map<String, Object> collect() {
        MemoryMXBean bean = ManagementFactory.getMemoryMXBean();
        Map<String, Object> map = new HashMap<>();
        map.put("jvm.memory.used", bean.getHeapMemoryUsage().getUsed());
        map.put("jvm.memory.max", bean.getHeapMemoryUsage().getMax());
        return map;
    }
}
