FROM openjdk:8-jdk-alpine as builder

LABEL version="0.1"
LABEL description="Employee management API"
LABEL maintainer="Soumik Das -- soumik.uxd@gmail.com"
LABEL source_url=""

WORKDIR app

# Copy maven artifacts
COPY mvnw .
COPY .mvn .mvn
COPY pom.xml .
RUN chmod +x ./mvnw
# download the dependency if needed or if the pom file is changed
RUN ./mvnw dependency:go-offline -B

# Copy sources
COPY src src

# Build the package and copy to target path
RUN ./mvnw clean package verify -DskipTests

FROM openjdk:8-jre-alpine
ARG DEPENDENCY=/app/target
ENV LANG C.UTF-8

# Copy the dependency application file from build stage artifact
COPY --from=builder ${DEPENDENCY}/*.jar .

ENTRYPOINT [ "java", "-jar", "employees-api.jar" ]