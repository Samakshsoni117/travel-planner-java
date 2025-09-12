# Stage 1: Build the application with Maven
FROM maven:3.8.5-openjdk-17 AS build
WORKDIR /app
COPY . .
RUN mvn clean install -DskipTests

# Stage 2: Run the application in a lightweight JRE environment
FROM eclipse-temurin:17-jre-focal
WORKDIR /app
COPY --from=build /app/target/travel-planner-0.0.1-SNAPSHOT.jar app.jar
EXPOSE 8080
ENTRYPOINT ["java","-jar","app.jar"]
```

#### Step 2: Push the New `Dockerfile` to GitHub

Render needs to see this new instruction file in your repository.

1.  Go to your terminal in VS Code.
2.  Run these three commands to upload the new file:
    ```bash
    git add Dockerfile
    git commit -m "feat: Add Dockerfile for Render deployment"
    git push
    
