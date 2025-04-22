# Step 1: Build the application with Maven
FROM maven:3.8.4-openjdk-17-slim AS build

WORKDIR /app

# Copy the pom.xml and the source code
COPY pom.xml .
COPY src ./src

# Run Maven to build the application
RUN mvn clean package -DskipTests

# Step 2: Run the application with a JDK
FROM openjdk:17-slim

WORKDIR /app

# Copy the jar file from the build stage
COPY --from=build /app/target/*.jar app.jar

# Expose the port the application will run on
EXPOSE 8080

# Run the application
ENTRYPOINT ["java", "-jar", "app.jar"]
