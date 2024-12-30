FROM alpine/java:21-jdk

WORKDIR /app

COPY target/servicediscoveryservice-1.0.0.jar servicediscoveryservice.jar

ENTRYPOINT ["java", "-jar", "servicediscoveryservice.jar"]
