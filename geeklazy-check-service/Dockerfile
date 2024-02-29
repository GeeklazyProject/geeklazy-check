FROM openjdk:17-jdk

MAINTAINER geeklazy<geeklazy@163.com>

WORKDIR /geeklazy-check
COPY target/geeklazy-check-service.jar geeklazy-check.jar
COPY target/classes/application.yml application.yml

EXPOSE 8080

CMD ["java", "-jar", "geeklazy-check.jar", "application.yml"]