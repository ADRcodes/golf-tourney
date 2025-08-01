# ── STAGE 1: Build with Maven + Java 21 ──────────────────────────────
FROM maven:3.9.0-eclipse-temurin-21 AS build

WORKDIR /app
COPY pom.xml .
COPY src ./src

# Run full Maven build (includes tests)
RUN mvn package

# ── STAGE 2: Run with Java 21 JDK Alpine ──────────────────────────────
FROM eclipse-temurin:21-jdk-alpine

WORKDIR /app
# copy the fat JAR; adjust if you use a classifier
COPY --from=build /app/target/*.jar app.jar

ENTRYPOINT ["java", "-jar", "app.jar"]
