package jvm.monitor.core.collector;

import jvm.monitor.core.MetricsCollector;

import java.lang.management.GarbageCollectorMXBean;
import java.lang.management.ManagementFactory;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class GcMetricsCollector implements MetricsCollector {

    @Override
    public String domain() {
        return "gc";
    }

    @Override
    public Map<String, Object> collect() {
        List<GarbageCollectorMXBean> beans = ManagementFactory.getGarbageCollectorMXBeans();
        long totalCount = 0;
        long totalTime = 0;
        for (GarbageCollectorMXBean gc : beans) {
            totalCount += gc.getCollectionCount();
            totalTime += gc.getCollectionTime();
        }
        Map<String, Object> map = new HashMap<>();
        map.put("jvm.gc.count", totalCount);
        map.put("jvm.gc.pause", totalTime);
        return map;
    }
}
