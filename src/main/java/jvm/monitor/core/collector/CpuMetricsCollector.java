package jvm.monitor.core.collector;

import jvm.monitor.core.MetricsCollector;

import java.lang.management.ManagementFactory;
import java.lang.management.OperatingSystemMXBean;
import java.util.HashMap;
import java.util.Map;

public class CpuMetricsCollector implements MetricsCollector {
    @Override
    public String domain() { return "cpu"; }

    @Override
    public Map<String, Object> collect() {
        OperatingSystemMXBean bean = ManagementFactory.getOperatingSystemMXBean();
        Map<String, Object> map = new HashMap<>();
        map.put("system.load.average", bean.getSystemLoadAverage());
        // CPU 사용률은 com.sun.management.OperatingSystemMXBean 캐스팅 필요 (추후 확장)
        return map;
    }
}
