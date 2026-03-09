package com.shorturl.domain;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.Random;

@Entity
@Getter
@NoArgsConstructor
public class Url {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String originalUrl;

    @Column(nullable = false, unique = true)
    private String shortenUrlKey;

    @Column(nullable = false)
    private Long redirectCount;

    @Column(nullable = false, updatable = false)
    private LocalDateTime createdAt;

    private LocalDateTime updateAt;

    @ManyToOne
    private User user;

    public Url(String originalUrl, String shortenUrlKey, User user){
        this.originalUrl = originalUrl;
        this.shortenUrlKey = shortenUrlKey;
        this.redirectCount = 0L;
        this.createdAt = LocalDateTime.now();
        this.user = user;
    }

    public static String generateShortenUrlKey() {
        String base56 = "23456789ABCDEFGHJKLMNPQRSTUVWXYZabcdefghijkmnpqrstuvwxyz";
        StringBuilder key = new StringBuilder();
        Random random = new Random();

        for (int i=0; i<8; i++){
            int index = random.nextInt(base56.length());
            char c = base56.charAt(index);
            key.append(c);
        }

        return key.toString();
    }

    public void increaseRedirectCount() {
        this.redirectCount = this.redirectCount + 1;
    }
}
