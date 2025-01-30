FROM openjdk:21-jdk
WORKDIR /workspace/app

COPY .mvn .mvn
COPY mvnw .
COPY src src
COPY pom.xml .

ENTRYPOINT ["./mvnw","spring-boot:run"]