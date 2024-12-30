FROM alpine/java:21-jdk

WORKDIR /app

COPY target/api-gateway-service-1.0.0.jar api-gateway-service.jar

ENTRYPOINT ["java", "-jar", "api-gateway-service.jar"]
