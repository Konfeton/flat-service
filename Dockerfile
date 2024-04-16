FROM eclipse-temurin:21-jdk-alpine AS build
WORKDIR /build

COPY mvnw .
ADD .mvn ./.mvn

COPY pom.xml .
COPY src src

RUN --mount=type=cache,target=/root/.m2 ./mvnw -f pom.xml clean package -DskipTests


FROM eclipse-temurin:21-jre-alpine
WORKDIR /
COPY --from=build /build/target/*.jar docker-test-app.jar
ENTRYPOINT ["java", "-jar", "docker-test-app.jar"]