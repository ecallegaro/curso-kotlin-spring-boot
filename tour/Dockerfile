FROM openjdk:8
MAINTAINER Evandro Callegaro
WORKDIR /opt/app

ARG JAR_FILE=target/tour-0.0.1-SHAPSHOT.jar
COPY ${JAR_FILE} app.jar
ENTRYPOINT ["java","-jar","app.jar"]