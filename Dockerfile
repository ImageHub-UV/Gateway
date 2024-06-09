ARG MSVC_NAME=msvc-gateway

FROM eclipse-temurin:21-jdk-alpine as builder

ARG MSVC_NAME

WORKDIR /app/${MSVC_NAME}

COPY ./.mvn ./.mvn
COPY ./mvnw .
COPY ./mvnw.cmd .
COPY ./pom.xml .

RUN ./mvnw dependency:go-offline

COPY ./src ./src

RUN ./mvnw clean package -DskipTests

FROM eclipse-temurin:21-jdk-alpine

ARG MSVC_NAME

WORKDIR /app

RUN mkdir ./logs

ARG TARGET_FOLFER=/app/${MSVC_NAME}/target

COPY --from=builder $TARGET_FOLFER/msvc-gateway-0.0.1-SNAPSHOT.jar .

EXPOSE 8090

CMD ["java", "-jar", "msvc-gateway-0.0.1-SNAPSHOT.jar"]

