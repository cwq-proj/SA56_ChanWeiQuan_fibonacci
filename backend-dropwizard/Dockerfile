FROM eclipse-temurin:20-jdk-alpine
WORKDIR /
ADD target/backend-dropwizard-1.0-SNAPSHOT.jar app.jar
EXPOSE 8080
ENTRYPOINT ["java", "-jar", "app.jar", "server"]

