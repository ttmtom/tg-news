FROM openjdk:21-jdk-slim
ARG JAR_FILE=build/*.jar
COPY ${JAR_FILE} /app/
ENTRYPOINT ["java","-jar","/TGNotification-0.0.1-SNAPSHOT.jar"]