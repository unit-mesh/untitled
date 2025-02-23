# Build stage
FROM gradle:8.1.1-jdk18 AS build
WORKDIR /app
COPY . .
RUN gradle clean build -x test

# Run stage
FROM openjdk:18-jdk-slim
WORKDIR /app
COPY --from=build /app/build/libs/*.jar /app/app.jar
EXPOSE 3000
ENTRYPOINT ["java", "-jar", "/app/app.jar"]
