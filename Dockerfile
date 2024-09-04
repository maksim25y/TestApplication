FROM maven:3-eclipse-temurin-11 AS build
COPY . /app
WORKDIR /app
RUN mvn package

FROM eclipse-temurin:11
COPY --from=build /app/target/TestApplication-0.0.1-SNAPSHOT.jar /app.jar
CMD ["java", "-jar", "/app.jar"]