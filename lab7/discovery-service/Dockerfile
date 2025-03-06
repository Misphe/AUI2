FROM eclipse-temurin:17-jdk-focal

WORKDIR /app

COPY target/discovery-service-0.0.1-SNAPSHOT.jar discovery-app.jar

EXPOSE 8761

ENTRYPOINT ["java", "-jar", "discovery-app.jar"]
