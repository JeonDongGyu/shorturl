package com.shorturl.presentation;

import lombok.Getter;

@Getter
public class ShortenUrlCreateResponseDto {
    private final String originalUrl;
    private final String shortenUrlKey;

    public ShortenUrlCreateResponseDto(String originalUrl, String shortenUrlKey){
        this.originalUrl = originalUrl;
        this.shortenUrlKey = shortenUrlKey;
    }
}
