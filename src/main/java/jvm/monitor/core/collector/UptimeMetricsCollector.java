package jvm.monitor.core.collector;

import jvm.monitor.core.MetricsCollector;

import java.lang.management.ManagementFactory;
import java.lang.management.RuntimeMXBean;
import java.util.HashMap;
import java.util.Map;

public class UptimeMetricsCollector implements MetricsCollector {
    @Override
    public String domain() { return "uptime"; }

    @Override
    public Map<String, Object> collect() {
        RuntimeMXBean bean = ManagementFactory.getRuntimeMXBean();
        Map<String, Object> map = new HashMap<>();
        map.put("jvm.uptime", bean.getUptime());
        return map;
    }
}
