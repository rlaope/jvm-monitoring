package jvm.monitor.core;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class MetricsFactory {
    private final List<MetricsCollector> collectors;

    public MetricsFactory(List<MetricsCollector> collectors) {
        this.collectors = collectors;
    }

    public Map<String, Object> collectAll() {
        return collectors.stream()
                .flatMap(c -> c.collect().entrySet().stream())
                .collect(Collectors.toMap(
                        Map.Entry::getKey,
                        Map.Entry::getValue
                ));
    }
}
