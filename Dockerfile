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