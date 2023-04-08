package me.developery.actuatorstudy.counter;

import io.micrometer.core.instrument.Counter;
import io.micrometer.core.instrument.MeterRegistry;
import jakarta.annotation.PostConstruct;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class MyHttpRequestManager {

    private final MeterRegistry meterRegistry;  // 생성자 주입

    private Counter httpRequestCounter;

    /**
     * registry 에 등록
     */
    @PostConstruct
    void init() {
        httpRequestCounter = Counter.builder("myHttpRequest")
                .register(meterRegistry);
    }

    /**
     * counter 증가시킬 필요가 있을때 외부에서 이 메서드를 호출
     */
    public void increase() {
        httpRequestCounter.increment();
    }
}
