package com.shorturl.presentation;

import lombok.Getter;

@Getter
public class ShortenUrlInformationDto {
    private final String originalUrl;
    private final String shortenUrlKey;
    private final Long redirectCount;

    public ShortenUrlInformationDto(String originalUrl, String shortenUrlKey, Long redirectCount){
        this.originalUrl = originalUrl;
        this.shortenUrlKey = shortenUrlKey;
        this.redirectCount = redirectCount;
    }
}
