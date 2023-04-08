package me.developery.actuatorstudy.counter;

import io.micrometer.core.instrument.Counter;
import io.micrometer.core.instrument.MeterRegistry;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class MyQueueManagerWithTags {

    private final MeterRegistry meterRegistry;  // 생성자 주입

    public void push() {
        Counter.builder("myQueueCounter")
                .tag("type", "push")
                .register(meterRegistry)
                .increment();
    }

    public void pop() {
        Counter.builder("myQueueCounter")
                .tag("type", "pop")
                .register(meterRegistry)
                .increment();
    }
}
