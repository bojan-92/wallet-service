# wallet-service
## Built With
 - [Maven](https://maven.apache.org/) - Dependency Management
 - [Lombok](https://projectlombok.org/) - for Intellij IDEA you have to install Lombok plugin
 - [JDK-11](https://www.oracle.com/java/technologies/javase-jdk8-downloads.html) - Java™ Platform, Standard Edition Development Kit
 - [H2 Database](https://en.wikipedia.org/wiki/H2_(DBMS)) - Relational database management system written in Java
 - [git](https://git-scm.com/) - Version control system
 - [Spring Boot](https://spring.io/projects/spring-boot) - Framework for development of Spring Applications
 - [Rest Assured](https://rest-assured.io/) - Java library for testing Restful Web services.
 - [JUnit](https://junit.org/junit5/) - Testing framework for Java
 - [Docker](https://www.docker.com/) - Open source containerization platform
 - [Open API Specification](https://swagger.io/specification/) - REST API specification

## External Tools Used
 - [Postman](https://www.postman.com/) - API Development Environment

## How to run the application
The first way to run this app:
 - In order to run this app you should pull docker image from this location: https://hub.docker.com/repository/docker/bojan92/service-wallet
 - Following steps should run the app without any additional changes ( you should have Docker installed locally):
  1. `docker pull bojan92/service-wallet:latest`
  2. `docker images`
  3. Find an image with the name bojan92/service-wallet and then run the command: `docker run -p 8080:8080 bojan92/service-wallet:latest` 
The second way to run this app:
 1. Checkout this code
 2. Navigate to project folder where file `docker-compose.yaml` exists
 5. Run command: `docker-compose up`
 6. Service is available on `localhost:8080` (for more details check wallet_api.yaml)

If you want to check data in H2 database, using browser navigate to `localhost:8080/h2-console` and set parameters as shown on the picture below:

![Screenshot](https://user-images.githubusercontent.com/15815459/150154770-e120b0f7-f213-4cba-b4be-235552528c74.png)
