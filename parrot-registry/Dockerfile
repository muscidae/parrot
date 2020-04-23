# Docker image for springboot file run
# VERSION 0.0.1
# Author: muscidae
FROM openjdk:latest
MAINTAINER muscidae <muscidae@qq.com>
VOLUME /parrot/parrot-registry
ADD target/${JAR_FILE} app.jar
RUN bash -c 'touch /app.jar'
ENTRYPOINT ["java","-Djava.security.egd=file:/dev/./urandom","-Dfile.encoding=utf-8","-Dloader.path=lib/","-jar","app.jar"]