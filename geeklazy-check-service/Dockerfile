FROM openjdk:17-jdk

MAINTAINER geeklazy<geeklazy@163.com>

WORKDIR /geeklazy-check
COPY target/geeklazy-check-service.jar geeklazy-check.jar
COPY target/classes/application.yml application.yml

ENV SPECTRUM_ADDRESS=""
ENV SPECTRUM_KEY=""
ENV SPECTRUM_SECRET=""

EXPOSE 8080

CMD ["java", "-jar", "geeklazy-check.jar", "application.yml", "--Dsystem.config.address=${SPECTRUM_ADDRESS}", "--Dspectrum.key=$SPECTRUM_KEY", "--Dspectrum.secret=${SPECTRUM_SECRET}", "--DENVIRONMENT=geeklazy", "--DSERVICE_NAME=geeklazy-check", "--DAPP_VERSION=v1"]