FROM openjdk:17-jdk-slim
ARG JAR_FILE=target/pharmacy.jar
COPY ${JAR_FILE} app.jar
ENTRYPOINT ["java", "-jar", "/app.jar"]


# docker-compose up --build  // run docker compose file
