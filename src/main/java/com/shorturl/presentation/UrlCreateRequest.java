package com.shorturl.presentation;

import jakarta.validation.constraints.NotNull;
import lombok.Getter;

//클라이언트가 보내는 데이터를 저장
@Getter
public class UrlCreateRequest {
    @NotNull // 필수값 null이면 안됨
    private String originalUrl;

    @NotNull
    private Long userId;

//        public String getOriginalUrl() {
//            return originalUrl;
//
//        }
}

