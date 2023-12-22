FROM openjdk:17

ARG JAR_FILE=target/*.jar

COPY ${JAR_FILE} eureka-service-registry.jar

ENTRYPOINT ["java" , "-jar" , "/eureka-service-registry.jar"]

EXPOSE 8761