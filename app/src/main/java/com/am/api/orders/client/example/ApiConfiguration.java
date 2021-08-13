/*
 * Copyright © 2021 Aram Meem Company Limited. All Rights Reserved.
 */

package com.am.api.orders.client.example;

import com.am.rest.client.catalog.ApiException;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.retry.RetryContext;
import org.springframework.retry.policy.SimpleRetryPolicy;
import org.springframework.retry.support.RetryTemplate;
import org.springframework.scheduling.annotation.EnableAsync;

import java.util.concurrent.TimeUnit;

@Configuration
@EnableAsync
public class ApiConfiguration {
    private static final int MAX_API_CALL_ATTEMPTS = 10;
    private static final long ATTEMPT_RETRY_TIMEOUT = TimeUnit.SECONDS.toMillis(10);

    private static SimpleRetryPolicy createRetryOnServerErrorPolicy() {
        return new SimpleRetryPolicy(MAX_API_CALL_ATTEMPTS) {
            @Override
            public boolean canRetry(RetryContext context) {
                if (super.canRetry(context)) {
                    if (context.getLastThrowable() == null) {
                        return true;
                    }
                    if (context.getLastThrowable() instanceof ApiException) {
                        ApiException e = (ApiException) context.getLastThrowable();
                        return e.getCode() >= 500 && e.getCode() < 600;
                    }
                }
                return false;
            }
        };
    }

    @Bean
    public RetryTemplate apiCallRetryTemplate() {
        return RetryTemplate.builder()
                .fixedBackoff(ATTEMPT_RETRY_TIMEOUT)
                .customPolicy(createRetryOnServerErrorPolicy())
                .build();
    }
}
