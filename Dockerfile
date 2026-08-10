FROM eclipse-temurin:25-jre-alpine

WORKDIR /app
COPY target/*.jar app.jar

ENV MYSQL_HOST=host.docker.internal
EXPOSE 8080

RUN addgroup -S spring && adduser -S spring -G spring
USER spring:spring

ENTRYPOINT ["java", "-jar", "/app/app.jar"]
