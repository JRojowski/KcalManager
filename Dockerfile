# Use a base image with the Java runtime environment
FROM openjdk:17-jdk-alpine3.13

# Set the working directory inside the container
WORKDIR /food-information

# Copy the application JAR file and any required configuration files
COPY target/food-information-0.0.1-SNAPSHOT.jar /food-information

# Expose the port that the application listens on
EXPOSE 8000

# Set the command to run when the container starts
CMD ["java", "-jar", "food-information-0.0.1-SNAPSHOT.jar"]