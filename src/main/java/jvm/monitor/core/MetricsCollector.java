package jvm.monitor.core;

import java.util.Map;

public interface MetricsCollector {
    String domain();
    Map<String, Object> collect();
}
