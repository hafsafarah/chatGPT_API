# Use openjdk:17-jre-slim as the base image
FROM openjdk:latest

# Copy the compiled Spring Boot jar to the Docker image
COPY target/*.jar app.jar

# Define the command to run when the container starts
CMD ["java", "-jar", "app.jar"]
