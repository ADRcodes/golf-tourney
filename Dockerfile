# STAGE 1: Build with Maven + Java 21 (Alpine)
FROM maven:3.9.8-eclipse-temurin-21-alpine AS build

WORKDIR /app
COPY pom.xml .
COPY src ./src

# Run full Maven build
RUN mvn package

# STAGE 2: Run with Eclipse Temurin Java 21 (Alpine)
FROM eclipse-temurin:21-alpine

WORKDIR /app
COPY --from=build /app/target/*.jar app.jar

ENTRYPOINT ["java", "-jar", "app.jar"]
