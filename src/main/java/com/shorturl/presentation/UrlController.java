package com.shorturl.presentation;

import com.shorturl.application.UrlService;
import com.shorturl.domain.Url;
import com.shorturl.domain.User;
import com.shorturl.domain.UserRepository;
import io.swagger.v3.oas.annotations.Hidden;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;

import static org.springframework.http.ResponseEntity.ok;

@RestController
@Tag(name = "URL 단축 API", description = "URL 단축 및 리다이렉트 서비스")
public class UrlController {

    private final UrlService urlService;
    private final UserRepository userRepository;

    @Autowired
    public UrlController(UrlService urlService, UserRepository userRepository) {
        this.urlService = urlService;
        this.userRepository = userRepository;
    }

    @Hidden
    @GetMapping("/")
    public ResponseEntity<?> index() {
        HttpHeaders headers = new HttpHeaders();
        headers.setLocation(URI.create("/index.html"));
        return new ResponseEntity<>(headers, HttpStatus.FOUND);
    }

    @Operation(summary = "URL 단축 생성", description = "원본 URL을 받아서 단축 URL을 생성합니다")
    @PostMapping("/shortenUrl")
    public ResponseEntity<UrlCreateResponse> createShortenUrl(@Valid @RequestBody UrlCreateRequest request){

        User user = userRepository.findById(request.getUserId())
                .orElseThrow(() -> new IllegalArgumentException("존재하지 않는 사용자입니다."));
        Url url =  urlService.generateShortenUrl(request.getOriginalUrl(), user);
        UrlCreateResponse responseDto = new UrlCreateResponse(url.getOriginalUrl(), url.getShortenUrlKey());
        return ResponseEntity.ok(responseDto);
    }

    @Operation(summary = "단축 URL 리다이렉트", description = "단축 URL로 접속하면 원본 URL로 이동합니다")
    @GetMapping("/coding/{shortenUrlKey}")
    public ResponseEntity<?> redirectShortenUrl(@PathVariable String shortenUrlKey){
        String url = urlService.getOriginalUrlByShortenUrlKey(shortenUrlKey);

        //헤더 생성
        HttpHeaders headers = new HttpHeaders();

        //Location에 원본 URL 설정
        headers.setLocation(URI.create(url));

        //301 상태코드와 함께 전달
        return new ResponseEntity<>(headers, HttpStatus.FOUND);
    }

    @Operation(summary = "단축 URL 정보 조회", description = "단축 URL의 클릭 수 등 정보를 조회합니다.")
    @GetMapping("/shortenUrl/{shortenUrlKey}")
    public ResponseEntity<UrlInformation> getShortenUrlInformation(@PathVariable String shortenUrlKey){
        Url url = urlService.getShortenUrlInformationByShortenUrlKey(shortenUrlKey);
        UrlInformation informationDto = new UrlInformation(url.getOriginalUrl(),url.getShortenUrlKey(), url.getRedirectCount());

        return ResponseEntity.ok(informationDto);
    }

}
