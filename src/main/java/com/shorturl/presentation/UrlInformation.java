package com.shorturl.presentation;

import lombok.Getter;

@Getter
public class UrlInformation {
    private final String originalUrl;
    private final String shortenUrlKey;
    private final Long redirectCount;

    public UrlInformation(String originalUrl, String shortenUrlKey, Long redirectCount){
        this.originalUrl = originalUrl;
        this.shortenUrlKey = shortenUrlKey;
        this.redirectCount = redirectCount;
    }
}
