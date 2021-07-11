package com.attijari.repository;

import com.attijari.domain.AccessToken;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Date;
import java.util.List;

@Repository
public interface AccessTokenRepository extends JpaRepository<AccessToken, String> {

    List<AccessToken> findAllByCreationDateBeforeAndNumberOfUseGreaterThanEqual(Date creationDateLimit, int numberOfUse);
}
