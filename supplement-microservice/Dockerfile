# Start with a base image containing Java runtime
FROM eclipse-temurin:21-jdk

# Information about the maintainer
LABEL org.opencontainers.image.authors="med.com"

# Add the application's jar to the image
COPY target/supplement-microservice-0.0.1-SNAPSHOT.jar supplement-microservice-0.0.1-SNAPSHOT.jar

# Execute the application
ENTRYPOINT ["java", "-jar", "supplement-microservice-0.0.1-SNAPSHOT.jar"]
