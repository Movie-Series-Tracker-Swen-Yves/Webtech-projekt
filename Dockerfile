# -------- Stage 1: Build --------
FROM gradle:8.9-jdk21 AS build

# Arbeitsverzeichnis im Build-Container
WORKDIR /home/gradle/src

# gesamtes Projekt kopieren (inkl. Gradle Dateien)
COPY --chown=gradle:gradle . .

# Spring Boot Jar NUR für das todo.app Modul bauen, Tests überspringen
RUN gradle :todo.app:bootJar --no-daemon -x test

# -------- Stage 2: Run --------
FROM eclipse-temurin:21-jre

# Arbeitsverzeichnis im Runtime-Container
WORKDIR /app

# das gebaute Jar aus dem Modul kopieren
COPY --from=build /home/gradle/src/todo.app/build/libs/*.jar app.jar

# Render setzt PORT als Env Var, in application.properties hast du server.port=${PORT:8080}
EXPOSE 8080

ENTRYPOINT ["java","-jar","/app/app.jar"]