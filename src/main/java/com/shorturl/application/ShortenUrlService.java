package com.shorturl.application;

import com.shorturl.domain.ShortenUrl;
import com.shorturl.domain.ShortenUrlRepository;
import com.shorturl.domain.User;
import com.shorturl.exception.UrlNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class ShortenUrlService {

    private final ShortenUrlRepository shortenUrlRepository;

    @Autowired
    public ShortenUrlService(ShortenUrlRepository shortenUrlRepository){
        this.shortenUrlRepository = shortenUrlRepository;
    }

    //URL을 받아서 단축 URL 생성 후 저장
    @Transactional
    public ShortenUrl generateShortenUrl(String originalUrl, User user){
        // http:// 없으면 자동 추가
        if (!originalUrl.startsWith("http://") && !originalUrl.startsWith("https://")) {
            originalUrl = "https://" + originalUrl;
        }

        String shortenUrlKey = ShortenUrl.generateShortenUrlKey();
        ShortenUrl shortenUrl = new ShortenUrl(originalUrl, shortenUrlKey, user);
        shortenUrlRepository.save(shortenUrl);
        return shortenUrl;
    }

    //단축키를 받아서 원본 URL을 찾고 리다이렉트로 카운트 증가시킨 뒤 반환
    @Transactional
    public String getOriginalUrlByShortenUrlKey(String shortenUrlKey){
        ShortenUrl shortenUrl = shortenUrlRepository.findByShortenUrlKey(shortenUrlKey).orElseThrow(() -> new UrlNotFoundException("존재하지 않는 URL입니다."));
        shortenUrl.increaseRedirectCount();
        shortenUrlRepository.save(shortenUrl);
        return shortenUrl.getOriginalUrl();
    }

    public ShortenUrl getShortenUrlInformationByShortenUrlKey(String shortenUrlKey){
        return shortenUrlRepository.findByShortenUrlKey(shortenUrlKey).orElseThrow(() -> new UrlNotFoundException("존재하지 않는 URL입니다."));
    }
}
