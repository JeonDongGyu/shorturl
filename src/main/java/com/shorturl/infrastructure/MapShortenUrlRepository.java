//package com.shorturl.infrastructure;
//
//import com.shorturl.domain.ShortenUrl;
//import com.shorturl.domain.ShortenUrlRepository;
//import org.springframework.stereotype.Repository;
//
//import java.util.Map;
//import java.util.concurrent.ConcurrentHashMap;
//
//@Repository
//public class MapShortenUrlRepository implements ShortenUrlRepository {
//
//    private Map<String, ShortenUrl> shortenUrls = new ConcurrentHashMap<>();
//
//    @Override
//    public void saveShortenUrl(ShortenUrl shortenUrl) {
//        shortenUrls.put(shortenUrl.getShortenUrlKey(), shortenUrl);
//    }
//
//    @Override
//    public ShortenUrl findShortenUrlByShortenUrlKey(String shortenUrlKey) {
//        return shortenUrls.get(shortenUrlKey);
//    }
//}
