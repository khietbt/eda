FROM alpine/java:21-jre

WORKDIR /app

COPY target/user-service-1.0.0.jar user-service.jar

ENTRYPOINT ["java", "-jar", "user-service.jar"]
