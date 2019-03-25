FROM openjdk:jre-stretch

RUN mkdir -p /opt/apps/tcpDUmp
COPY ./build/libs/vertx-gradle-starter-fat.jar /opt/apps/tcpDUmp
WORKDIR /opt/apps/tcpDUmp

ENTRYPOINT ["java", "-jar", "/opt/apps/tcpDUmp/vertx-gradle-starter-fat.jar"]

EXPOSE 8080
