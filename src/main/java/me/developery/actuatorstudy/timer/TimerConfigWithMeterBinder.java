package me.developery.actuatorstudy.timer;

import io.micrometer.core.instrument.FunctionTimer;
import io.micrometer.core.instrument.MeterRegistry;
import io.micrometer.core.instrument.binder.MeterBinder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.concurrent.TimeUnit;

@Configuration
public class TimerConfigWithMeterBinder {

    @Bean
    public MeterBinder myTimerWithMeterBinder(MyTimerManager myTimerManager) {
        return new MeterBinder() {
            @Override
            public void bindTo(MeterRegistry registry) {
                FunctionTimer functionTimer = FunctionTimer.builder("my.timerWithMeterBinder.latency", myTimerManager,
                                m -> {
                                    return m.getCount();
                                },
                                m -> {
                                    return m.getTotalTime();
                                },
                                TimeUnit.SECONDS)
                        .register(registry);
            }
        };
    }
}
