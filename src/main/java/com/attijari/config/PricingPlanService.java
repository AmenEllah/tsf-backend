package com.attijari.config;

import io.github.bucket4j.Bandwidth;
import io.github.bucket4j.Bucket;
import io.github.bucket4j.Bucket4j;
import io.github.bucket4j.Refill;
import org.springframework.stereotype.Component;

import java.time.Duration;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

@Component
public class PricingPlanService {

    private static long CAPACITY = 200;

    private static long REFILL = 200;

    private static long DURATION = 1;

    private final Map<String, Bucket> cache = new ConcurrentHashMap<>();
    public Bucket resolveBucket(String apiKey) {
        return cache.computeIfAbsent(apiKey, this::newBucket);
    }

    private Bucket newBucket(String apiKey) {
        return Bucket4j.builder()
            .addLimit(Bandwidth.classic(CAPACITY, Refill.intervally(REFILL, Duration.ofMinutes(DURATION))))
            .build();
    }
}
