package jvm.monitor.core.collector;

import jvm.monitor.core.MetricsCollector;

import java.lang.management.ClassLoadingMXBean;
import java.lang.management.ManagementFactory;
import java.util.HashMap;
import java.util.Map;

public class ClassLoadingMetricsCollector implements MetricsCollector {

    @Override
    public String domain() {
        return "classes";
    }

    @Override
    public Map<String, Object> collect() {
        ClassLoadingMXBean bean = ManagementFactory.getClassLoadingMXBean();
        Map<String, Object> map = new HashMap<>();
        map.put("jvm.classes.loaded", bean.getLoadedClassCount());
        return map;
    }
}
