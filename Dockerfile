FROM maven:3.9-eclipse-temurin-21 AS build
WORKDIR /app
COPY pom.xml .
COPY src ./src
RUN mvn clean package -DskipTests



FROM eclipse-temurin:21-jdk-alpine
WORKDIR /app
COPY --from=build /app/target/*.jar app.jar
EXPOSE 8080
ENTRYPOINT ["java", "-jar", "app.jar"]



RUN addgroup -S clyvogroup && adduser -S clyvouser -G clyvogroup
WORKDIR /app
COPY --from=build /app/target/*.jar app.jar
RUN chown clyvouser:clyvogroup app.jar
USER clyvouser
ENTRYPOINT ["java", "-jar", "app.jar"]