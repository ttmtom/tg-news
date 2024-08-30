FROM openjdk:21-jdk-slim
ARG JAR_FILE=target/*.jar
COPY ${JAR_FILE} /app/
ENTRYPOINT ["java","-jar","/app.jar"]