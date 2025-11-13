# -------- Stage 1: Build --------
FROM gradle:8.9-jdk21 AS build
WORKDIR /app
COPY src/main/java/htw/webtech/todo/app .
RUN ./gradlew bootJar --no-daemon

# -------- Stage 2: Run --------
FROM eclipse-temurin:21-jre
WORKDIR /app
# das gebaute Jar rüberkopieren
COPY --from=build /app/build/libs/*.jar app.jar
# Render setzt PORT als Env Var
ENV PORT=8080
EXPOSE 8080
ENTRYPOINT ["java","-jar","/app/app.jar"]
