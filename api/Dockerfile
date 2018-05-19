FROM openjdk:8-jdk-alpine
ARG JAR_FILE
ADD ${JAR_FILE} app.jar
ADD config/application.properties config/application.properties
ENTRYPOINT ["java", "-jar", "/app.jar"]