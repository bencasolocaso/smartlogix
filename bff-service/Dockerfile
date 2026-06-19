# Multi-stage build for BFF Service (Java 21 / Spring Boot)
FROM eclipse-temurin:21-jdk-jammy AS builder
WORKDIR /build
COPY pom.xml .
COPY .mvn .mvn
COPY mvnw .
RUN chmod +x mvnw && ./mvnw dependency:go-offline
COPY src src
RUN ./mvnw clean package -DskipTests

FROM eclipse-temurin:21-jre-jammy
WORKDIR /app
COPY --from=builder /build/target/*.jar app.jar
EXPOSE 8084
CMD ["java", "-jar", "app.jar"]
