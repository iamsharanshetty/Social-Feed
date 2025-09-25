# Use an official OpenJDK runtime as a parent image
FROM openjdk:17-jdk-slim

# Set the working directory in the container
WORKDIR /app

# Copy the Maven wrapper files first
COPY .mvn/ .mvn
COPY mvnw mvnw
COPY mvnw.cmd mvnw.cmd

# Copy pom.xml
COPY pom.xml pom.xml

# Fix line endings and give execute permission to the Maven wrapper
RUN sed -i 's/\r$//' mvnw && chmod +x mvnw

# Download dependencies (for better layer caching)
RUN ./mvnw dependency:go-offline -B || true

# Copy the source code
COPY src src

# Build the application
RUN ./mvnw clean package -DskipTests

# Expose the port the app runs on
EXPOSE 8080

# Run the Spring Boot application
CMD ["java", "-jar", "target/social-media-api-1.0.0.jar"]