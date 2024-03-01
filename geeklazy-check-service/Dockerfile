FROM openjdk:17-jdk

MAINTAINER geeklazy<geeklazy@163.com>

WORKDIR /geeklazy-check
COPY target/geeklazy-check-service.jar geeklazy-check.jar
COPY target/classes/application.yml application.yml

ENV DB_HOST=""
ENV DB_SCHEMA=""
ENV DB_PORT=3306
ENV DB_USERNAME="root"
ENV DB_PASSWORD=""

EXPOSE 8080

CMD ["java", "-jar", "geeklazy-check.jar", "application.yml", "--Ddatabase.host=${DB_HOST}", "--Ddatabase.schema=$DB_SCHEMA", "--Ddatabase.port=${DB_PORT}", "--Ddatabase.username=${DB_USERNAME}", "--Ddatabase.password=${DB_PASSWORD}"]