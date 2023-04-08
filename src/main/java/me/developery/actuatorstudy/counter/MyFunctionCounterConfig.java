package me.developery.actuatorstudy.counter;

import io.micrometer.core.instrument.FunctionCounter;
import io.micrometer.core.instrument.MeterRegistry;
import jakarta.annotation.PostConstruct;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Configuration;

@Configuration
@RequiredArgsConstructor
public class MyFunctionCounterConfig {
    private final MyHttpRequestManagerWithoutMicrometer myManager;
    private final MeterRegistry meterRegistry;

    @PostConstruct
    void init() {
        FunctionCounter.builder("myHttpRequestWithoutMicrometer", myManager, myManager -> {
            return myManager.getCount();
        })
        .register(meterRegistry);
    }
}
