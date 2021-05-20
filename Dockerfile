FROM adoptopenjdk/openjdk11:ubi
RUN mkdir /opt/app
COPY target/heroes-0.0.1-SNAPSHOT.jar /opt/app
CMD ["java", "-jar", "/opt/app/heroes-0.0.1-SNAPSHOT.jar"]