package com.wilfred.security.springsecurity.monitor;

import org.springframework.boot.actuate.health.Health;
import org.springframework.boot.actuate.health.HealthIndicator;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.Map;
import java.util.Random;

@Component
public class DbHealthIndicator implements HealthIndicator {

    @Override
    public Health health() {
        if(isDBHealthy()){
            Map<String, String> healthyMap = new HashMap<>();
            healthyMap.put("External Db SVC","Healthy");
            return Health.up().withDetails(healthyMap).build();
        }
        Map<String, String> unHealthyMap = new HashMap<>();
        unHealthyMap.put("External Db SVC","Is Not-Healthy");
        return Health.down().withDetails(unHealthyMap).build();
    }

    @Override
    public Health getHealth(boolean includeDetails) {
        return HealthIndicator.super.getHealth(includeDetails);
    }

    private boolean isDBHealthy() {
        Random random = new Random();
        return random.nextBoolean();
    }
}
