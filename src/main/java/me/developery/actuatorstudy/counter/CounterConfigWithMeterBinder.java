package me.developery.actuatorstudy.counter;

import io.micrometer.core.instrument.FunctionCounter;
import io.micrometer.core.instrument.MeterRegistry;
import io.micrometer.core.instrument.binder.MeterBinder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class CounterConfigWithMeterBinder {

    @Bean
    public MeterBinder myTimerWithMeterBinder(MyHttpRequestManagerWithoutMicrometer manager) {
        return new MeterBinder() {
            @Override
            public void bindTo(MeterRegistry registry) {
                FunctionCounter.builder("myHttpRequestWithMeterBinder", manager, m -> {
                            return m.getCount();
                        })
                        .register(registry);
            }
        };
    }
}
