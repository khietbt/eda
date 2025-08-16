FROM alpine/java:21-jre

WORKDIR /app

COPY target/service-discovery-service-1.0.0.jar service-discovery-service.jar

ENTRYPOINT ["java", "-jar", "service-discovery-service.jar"]
