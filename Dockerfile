# Stage 1: Build with Maven + JDK
FROM maven:3.9.6-eclipse-temurin-21 AS builder

WORKDIR /app

COPY pom.xml .
RUN mvn dependency:go-offline

COPY src ./src
RUN mvn clean package -DskipTests


# Stage 2: Lightweight JRE image for running the app, JRE-only, Alpine small OS
FROM eclipse-temurin:21-jre-alpine

WORKDIR /app

# Copy built JAR
COPY --from=builder /app/target/*.jar app.jar

EXPOSE 8080

ENTRYPOINT ["java", "-jar", "app.jar"]
