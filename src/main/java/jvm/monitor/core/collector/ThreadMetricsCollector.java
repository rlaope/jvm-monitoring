package jvm.monitor.core.collector;

import jvm.monitor.core.MetricsCollector;

import java.lang.management.ManagementFactory;
import java.lang.management.ThreadMXBean;
import java.util.HashMap;
import java.util.Map;

public class ThreadMetricsCollector implements MetricsCollector {
    @Override
    public String domain() {
        return "threads";
    }

    @Override
    public Map<String, Object> collect() {
        ThreadMXBean bean = ManagementFactory.getThreadMXBean();
        Map<String, Object> map = new HashMap<>();
        map.put("jvm.thread.count", bean.getThreadCount());
        map.put("jvm.threads.daemon.count", bean.getDaemonThreadCount());
        return map;
    }
}
