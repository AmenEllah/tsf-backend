package com.attijari.config;

import io.github.bucket4j.Bucket;
import io.github.bucket4j.ConsumptionProbe;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@Component
public class RateLimitInterceptor implements HandlerInterceptor {

    private final PricingPlanService pricingPlanService;
    private final Logger log = LoggerFactory.getLogger(RateLimitInterceptor.class);

    public RateLimitInterceptor(PricingPlanService pricingPlanService) {
        this.pricingPlanService = pricingPlanService;
    }

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
        throws Exception {
        String path = request.getRequestURI();
        String remoteAddress = request.getHeader("X-Real-IP");
        String[] subPaths = path.split("/");
        String apiKey;
        if (subPaths.length >= 3) {
            apiKey = remoteAddress+ "|" + "/" + subPaths[1] + "/" + subPaths[2];
        } else {
            apiKey = remoteAddress+ "|" + path;
        }
        Bucket tokenBucket = pricingPlanService.resolveBucket(apiKey);
        ConsumptionProbe probe = tokenBucket.tryConsumeAndReturnRemaining(1);
        if (probe.isConsumed()) {
            response.addHeader("X-Rate-Limit-Remaining", String.valueOf(probe.getRemainingTokens()));
            return true;
        } else {
            long waitForRefill = probe.getNanosToWaitForRefill() / 1_000_000_000;
            log.info("Blocked client {} for {} seconds", remoteAddress, waitForRefill);
            response.addHeader("X-Rate-Limit-Retry-After-Seconds", String.valueOf(waitForRefill));
            response.sendError(HttpStatus.TOO_MANY_REQUESTS.value(),
                "You have exhausted your API Request Quota");
            return false;
        }
    }
}
