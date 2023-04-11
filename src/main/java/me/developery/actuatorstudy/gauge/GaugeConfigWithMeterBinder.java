package me.developery.actuatorstudy.gauge;

import io.micrometer.core.instrument.Gauge;
import io.micrometer.core.instrument.MeterRegistry;
import io.micrometer.core.instrument.binder.MeterBinder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class GaugeConfigWithMeterBinder {

    @Bean
    public MeterBinder queueSize(QueueManager queueManager) {
        return new MeterBinder() {
            @Override
            public void bindTo(MeterRegistry registry) {
                Gauge.builder("my.queue2.size", queueManager, queueManager -> {
                    return queueManager.getQueueSize();
                }).register(registry);
            }
        };
    }
}
