# Multi-stage Dockerfile: build with Maven, run with lightweight JRE

FROM maven:3.9.4-eclipse-temurin-17 AS build
WORKDIR /app
COPY pom.xml .
COPY src ./src

# Package the application (skip tests for faster build in Docker)
RUN mvn -B -DskipTests package

# Runtime image
FROM eclipse-temurin:17-jre
WORKDIR /app

# Copy the executable jar from the build stage
COPY --from=build /app/target/*.jar app.jar

EXPOSE 8080
ENTRYPOINT ["java", "-jar", "/app/app.jar"]

