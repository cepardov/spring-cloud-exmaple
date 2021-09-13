FROM openjdk:8-jdk-alpine
EXPOSE 8080
ADD build/libs/*.jar /app/app.jar
RUN apk update && apk add tzdata
WORKDIR /app
CMD java -jar app.jar