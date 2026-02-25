# 🔗 URL 단축 서비스

긴 URL을 짧게 줄여주는 서비스입니다.

## 🛠 기술 스택

- Java 17
- Spring Boot 4.0
- Spring Data JPA
- H2 Database
- Swagger (API 문서)

## 📁 프로젝트 구조
```
src/main/java/com/shorturl/
├── domain/          # 엔티티
├── application/     # 서비스 (비즈니스 로직)
├── presentation/    # 컨트롤러, DTO
├── exception/       # 예외 처리
└── infrastructure/  # (확장용)
```

## 🚀 실행 방법
```bash
./gradlew bootRun
```

## 📌 API 목록

| Method | URL | 설명 |
|--------|-----|------|
| POST | /shortenUrl | URL 단축 생성 |
| GET | /coding/{key} | 원본 URL로 리다이렉트 |
| GET | /shortenUrl/{key} | 단축 URL 정보 조회 |

## 📖 API 문서

서버 실행 후 접속: `http://localhost:8080/swagger-ui/index.html`