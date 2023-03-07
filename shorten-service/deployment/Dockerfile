FROM openjdk:11.0.7
RUN mkdir /opt/app
COPY application.jar /opt/app
COPY application.properties /opt/app
EXPOSE 8080
CMD ["java", "-jar", "/opt/app/application.jar"]
