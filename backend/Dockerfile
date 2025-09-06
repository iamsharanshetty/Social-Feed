# Use an official OpenJDK runtime as a parent image
FROM openjdk:17-jdk-slim

# Set the working directory in the container
WORKDIR /app

# Copy the Maven wrapper and pom.xml files
COPY .mvn/ .mvn
COPY mvnw mvnw
COPY pom.xml pom.xml

# Copy the source code
COPY src src

# Give execute permission to the Maven wrapper
RUN chmod +x mvnw

# Build the application
RUN ./mvnw clean package -DskipTests

# Expose the port the app runs on
EXPOSE 8080

# Run the Spring Boot application
CMD ["java", "-jar", "target/social-media-api-1.0.0.jar"]