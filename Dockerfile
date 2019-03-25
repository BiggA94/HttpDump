FROM openjdk:stretch

RUN mkdir -p /opt/apps/tcpDUmp
COPY . /opt/apps/tcpDUmp
WORKDIR /opt/apps/tcpDUmp

RUN ./gradlew shadowJar

ENTRYPOINT ["java", "-jar", "/opt/apps/tcpDUmp/build/libs/vertx-gradle-starter-fat.jar"]

EXPOSE 8080
