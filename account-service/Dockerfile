FROM openjdk:8-jdk-alpine
WORKDIR /app
COPY target/account-service-0.0.1-SNAPSHOT.jar /app/account-service-0.0.1-SNAPSHOT.jar
ENTRYPOINT ["java","-Djava.security.egd=file:/dev/./urandom","-jar","/app/account-service-0.0.1-SNAPSHOT.jar"]
