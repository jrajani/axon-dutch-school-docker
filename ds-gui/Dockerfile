FROM openjdk:8-jre-alpine
COPY target/ds-gui-1.0-SNAPSHOT.jar /opt/lib/ds-gui.jar
ENTRYPOINT ["java", "-Djava.security.egd=file:/dev/./urandom", "-Xms512M", "-Xmx512M", "-jar", "/opt/lib/ds-gui.jar"]
EXPOSE 8080