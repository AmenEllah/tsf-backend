package com.attijari.service.cron;

import com.attijari.domain.AccessToken;
import com.attijari.repository.AccessTokenRepository;
import org.springframework.scheduling.annotation.Scheduled;

import java.time.Instant;
import java.time.temporal.ChronoUnit;
import java.util.Date;
import java.util.List;

public class AccessTokensCron {


    private final AccessTokenRepository accessTokenRepository;

    public AccessTokensCron(AccessTokenRepository accessTokenRepository) {
        this.accessTokenRepository = accessTokenRepository;
    }

    @Scheduled(cron = "0 0 1 * * ?")
    private void removeUsedToken() {
        List<AccessToken> accessTokens = accessTokenRepository.findAllByCreationDateBeforeAndNumberOfUseGreaterThanEqual(Date.from(Instant.now().minus(1, ChronoUnit.MONTHS)), 1);
        accessTokenRepository.deleteAll(accessTokens);
    }
}
