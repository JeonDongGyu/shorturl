package com.shorturl.presentation;

import lombok.Getter;

@Getter
public class UrlCreateResponse {
    private final String originalUrl;
    private final String shortenUrlKey;

    public UrlCreateResponse(String originalUrl, String shortenUrlKey){
        this.originalUrl = originalUrl;
        this.shortenUrlKey = shortenUrlKey;
    }
}
