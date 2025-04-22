FROM openjdk:17
EXPOSE 8093
ADD "target/Api_getway-0.0.1-SNAPSHOT.jar" "APIMSP-docker.jar"
ENTRYPOINT ["java", "-jar", "APIMSP-docker.jar"]