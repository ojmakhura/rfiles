# Multi-module Spring Boot example

Modules:
- common: shared utilities
- core: core services depending on common
- webservice: Spring Boot application exposing a REST endpoint

Build and run:

1. Build the project:

    mvn -v && mvn clean package

2. Run the webservice module:

    cd webservice
    mvn spring-boot:run

Endpoint:

- GET http://localhost:8080/greet?name=YourName

Run tests:

    mvn test
# rfiles
