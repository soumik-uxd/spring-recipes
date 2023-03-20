FROM openjdk:17-jdk-slim as builder

LABEL version="0.1"
LABEL description="Paged Response Demo API"
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

RUN apt update && \
    apt -y install binutils && \
    jlink --module-path "$JAVA_HOME/jmods" \
    --add-modules java.compiler,java.sql,java.naming,java.management,java.instrument,java.rmi,java.desktop,jdk.internal.vm.compiler.management,java.xml.crypto,java.scripting,java.security.jgss,jdk.httpserver,java.net.http,jdk.naming.dns,jdk.crypto.cryptoki,jdk.unsupported \
    --verbose --strip-debug --compress 2 --no-header-files --no-man-pages --output /opt/jre-minimal

# Build the package and copy to target path
RUN ./mvnw clean package verify -DskipTests

FROM debian:bullseye-slim

ENV JAVA_HOME=/opt/jre-minimal
ENV PATH="$PATH:$JAVA_HOME/bin"
ARG DEPENDENCY=/app/target
ENV LANG C.UTF-8

COPY --from=builder ${JAVA_HOME} ${JAVA_HOME}

# Copy the dependency application file from build stage artifact
COPY --from=builder ${DEPENDENCY}/*.jar .

ENTRYPOINT [ "java", "-jar", "Pager-Hazelcast-Demo.jar" ]