FROM alpine/java:21-jdk

WORKDIR /app

COPY target/config-service-1.0.0.jar config-service.jar

ENTRYPOINT ["java", "-jar", "config-service.jar"]
