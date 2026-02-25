package com.shorturl.domain;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface ShortenUrlRepository extends JpaRepository<ShortenUrl, Long> {

    Optional<ShortenUrl> findByShortenUrlKey(String shortenUrlKey);

}
