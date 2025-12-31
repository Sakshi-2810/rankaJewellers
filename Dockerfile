# Use lightweight Java runtime
FROM eclipse-temurin:17-jre

# Set working directory inside container
WORKDIR /app

# Copy the Spring Boot jar into container
COPY target/*.jar app.jar

# Expose Spring Boot port
EXPOSE 8080

# Run the application
ENTRYPOINT ["java","-jar","app.jar"]
