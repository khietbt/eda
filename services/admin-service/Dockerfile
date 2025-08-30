FROM alpine/java:21-jre

WORKDIR /app

COPY target/admin-service-1.0.0.jar admin-service.jar

ENTRYPOINT ["java", "-jar", "admin-service.jar"]
