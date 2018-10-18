FROM openjdk:8-jre-alpine
COPY target/ds-query-1.0-SNAPSHOT.jar /opt/lib/ds-query.jar
ENTRYPOINT ["java", "-Djava.security.egd=file:/dev/./urandom", "-Xms512M", "-Xmx512M", "-jar", "/opt/lib/ds-query.jar"]
EXPOSE 8080