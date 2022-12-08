FROM openjdk:17-alpine
ADD target/travel-office-web.jar travel-office-web.jar
ENTRYPOINT ["java","-jar","travel-office-web.jar"]
EXPOSE 8080
