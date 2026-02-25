package com.shorturl.service;

import com.shorturl.application.ShortenUrlService;
import com.shorturl.domain.ShortenUrl;
import com.shorturl.exception.UrlNotFoundException;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.assertj.core.api.Assertions.*;

@SpringBootTest
public class ShortenUrlServiceTest {

    @Autowired
    private ShortenUrlService shortenUrlService;

    @Test
    @DisplayName("URL 단축 생성 테스트")
    void createShortenUrl(){
        //given
        String originalUrl = "http://www.naver.com";

        //when
        ShortenUrl result = shortenUrlService.generateShortenUrl(originalUrl);

        //then
        assertThat(result.getOriginalUrl()).isEqualTo(originalUrl);
        assertThat(result.getShortenUrlKey()).hasSize(8);
    }

    @Test
    @DisplayName("없는 URL 조회 시 예외 발생")
    void notFoundUrl() {
        //given
        String fakeKey = "abcd1234";

        //when&then
        assertThatThrownBy(() -> shortenUrlService.getOriginalUrlByShortenUrlKey(fakeKey))
                .isInstanceOf(UrlNotFoundException.class);
    }
}
