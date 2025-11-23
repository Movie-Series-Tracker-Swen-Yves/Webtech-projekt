# -------- Stage 1: Build --------
FROM gradle:8.9-jdk21 AS build
WORKDIR /app

# Wichtig: alles, was Gradle zum Bauen braucht
COPY build.gradle settings.gradle gradlew gradlew.bat ./
COPY gradle ./gradle
COPY src ./src

RUN chmod +x gradlew
RUN ./gradlew bootJar --no-daemon

# -------- Stage 2: Run --------
FROM eclipse-temurin:21-jre
WORKDIR /app

# das gebaute Jar rüberkopieren
COPY --from=build /app/build/libs/*.jar app.jar

# Render setzt PORT als Env Var, Spring liest sie bereits (server.port=${PORT:8080})
EXPOSE 8080

ENTRYPOINT ["java","-jar","/app/app.jar"]