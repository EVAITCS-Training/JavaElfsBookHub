# This Dockerfile builds a Spring Boot application using Maven in a multi-stage build.
# 
# 1. Uses an OpenJDK 21 Alpine base image for a lightweight Java environment.
# 2. Updates and upgrades Alpine packages.
# 3. Sets the working directory to /app.
# 4. Copies Maven wrapper scripts and configuration files.
# 5. Grants execute permissions to the Maven wrapper.
# 6. Resolves project dependencies to optimize build caching.
# 7. Copies the application source code.
# 8. Defines a 'development' stage that runs the Spring Boot application using the Maven wrapper.
# 9. Exposes port 8080 for the application.
FROM amazoncorretto:21-alpine AS build
RUN apk update && apk upgrade

WORKDIR /app

COPY mvnw mvnw.cmd ./
COPY .mvn/ .mvn/
COPY pom.xml ./

RUN chmod +x ./mvnw && ./mvnw dependency:resolve

COPY src ./src


FROM build AS development
CMD ["./mvnw", "spring-boot:run"]

EXPOSE 8080