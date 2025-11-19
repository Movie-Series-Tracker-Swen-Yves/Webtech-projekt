# Purpose
Movie/Series tracker REST API (CRUD + fetch from external movie API).

Stack: Java <17/21>, Spring Boot <3.5.6>, Gradle, Lombok, JUnit 5, (DB: <none/H2/Postgres>)

Run: ./gradlew bootRun
Test: ./gradlew test

Modules (fast map)
- controller/ – REST endpoints (FilmController, SerieController)
- service/ – business logic (FilmService, SerieService, MovieApiService)
- dto/ – API payloads
- model/ – Film, Serie entities
- CorsConfig – CORS setup

Pain points you want reviewed
- <e.g., CORS rules, DTO mapping, failing tests, API rate limits, packaging>