package me.developery.actuatorstudy;

import io.micrometer.core.instrument.Gauge;
import io.micrometer.core.instrument.MeterRegistry;
import io.micrometer.core.instrument.binder.MeterBinder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class MyStockMeterBinderConfig {

    @Bean
    public MeterBinder myStockMeterBinder(MyStockManager myStockManager) {
        return new MeterBinder() {
            @Override
            public void bindTo(MeterRegistry registry) {
                Gauge.builder("myStockCount", myStockManager, value -> {
                    return value.getStock();
                }).register(registry);

            }
        };
    }
}
