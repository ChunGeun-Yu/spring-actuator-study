package me.developery.actuatorstudy;

import org.springframework.boot.actuate.health.Health;
import org.springframework.boot.actuate.health.HealthIndicator;
import org.springframework.stereotype.Component;

@Component
public class MyCustomHealthIndicator implements HealthIndicator {
    @Override
    public Health health() {
        boolean status = getStatus();
        if ( status ) {
            Health upHealth = Health.up()
                    .withDetail("key1", "value1")
                    .withDetail("key2", "value2")
                    .build();
            return upHealth;
        }
        Health downHealth = Health.down()
                .withDetail("key3", "value3")
                .withDetail("key4", "value4")
                .build();

        return downHealth;
    }

    boolean getStatus() {
        // 현재시각이 짝수,홀수인지에 따라 up, down을 판단하는 것으로 대체
        if ( System.currentTimeMillis() % 2 == 0 )
            return true;
        return false;
    }
}
