package com.inventory.order.test.application.service;

import org.springframework.retry.annotation.Backoff;
import org.springframework.retry.annotation.Retryable;
import org.springframework.stereotype.Service;

@Service
public class RetryService {

	@Retryable(
	        maxAttempts = 3,
	        backoff = @Backoff(delay = 2000)
	    )
	    public void processEvent() {

	        System.out.println("Retry processing...");
	    }

}
