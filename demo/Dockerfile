# Use a base image with Java installed
FROM openjdk:17-jdk-alpine

# Set the working directory
WORKDIR /app

# Copy the compiled JAR file into the container
COPY target/demo-0.0.1-SNAPSHOT.jar app.jar

# Expose the application's port
EXPOSE 8080

# Run the application
ENTRYPOINT ["java", "-jar", "app.jar"]
