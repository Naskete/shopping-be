FROM openjdk:8
WORKDIR /
ADD shopping-0.0.1-SNAPSHOT.jar app.jar
ENTRYPOINT ["java","-jar","app.jar"]
EXPOSE 8088