# 🔗 Shortly - URL 단축 서비스

긴 URL을 짧게 줄여주고 클릭 수를 추적하는 서비스입니다.

## 🛠 기술 스택

| 구분 | 기술 |
|------|------|
| Backend | Java 17, Spring Boot 4.0 |
| Database | Spring Data JPA, H2 |
| API 문서 | Swagger (SpringDoc) |
| CI/CD | GitHub Actions |

## 📁 프로젝트 구조
```
src/main/java/com/shorturl/
├── domain/           # 엔티티 (ShortenUrl, User)
├── application/      # 서비스 (비즈니스 로직)
├── presentation/     # 컨트롤러, DTO
├── exception/        # 예외 처리
└── infrastructure/   # (확장용)
```

## 🚀 실행 방법
```bash
# 프로젝트 빌드
./gradlew build

# 서버 실행
./gradlew bootRun
```

서버 실행 후 `http://localhost:8080` 접속

## 📌 주요 기능

| 기능 | 설명 |
|------|------|
| 회원가입/로그인 | 이메일 기반 인증 |
| URL 단축 | 긴 URL을 8자리 키로 단축 |
| 리다이렉트 | 단축 URL 접속 시 원본으로 이동 |
| 클릭 추적 | 리다이렉트 횟수 기록 |

## 📡 API 목록

### 회원 API

| Method | URL | 설명 |
|--------|-----|------|
| POST | /users/register | 회원가입 |
| POST | /users/login | 로그인 |

### URL API

| Method | URL | 설명 |
|--------|-----|------|
| POST | /shortenUrl | URL 단축 생성 |
| GET | /coding/{key} | 원본 URL로 리다이렉트 |
| GET | /shortenUrl/{key} | 단축 URL 정보 조회 |

## 📖 API 문서

서버 실행 후 접속: `http://localhost:8080/swagger-ui/index.html`

## 🗄 ERD
```
┌──────────────┐       ┌──────────────────┐
│    User      │       │    ShortenUrl    │
├──────────────┤       ├──────────────────┤
│ id (PK)      │──────<│ id (PK)          │
│ email        │       │ originalUrl      │
│ password     │       │ shortenUrlKey    │
│ nickname     │       │ redirectCount    │
│ createdAt    │       │ user_id (FK)     │
└──────────────┘       │ createdAt        │
                       │ updatedAt        │
                       └──────────────────┘
```

## ✅ 구현 완료

- [x] JPA + H2 데이터베이스 연동
- [x] 회원가입/로그인
- [x] URL 단축 생성
- [x] 리다이렉트 및 클릭 수 추적
- [x] 예외 처리 (GlobalExceptionHandler)
- [x] 테스트 코드 (JUnit)
- [x] Swagger API 문서
- [x] GitHub Actions CI/CD
- [x] 프론트엔드 (로그인/회원가입 UI)

## 📝 License

MIT License