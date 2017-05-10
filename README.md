# Registration Web Service
#### This is a Springboot/H2/Java Registration Web Service code challenge implementing four API

#### Download project from github using local git:
>git clone https://github.com/at7000ft/RegisterRestServiceGradle

### Build an executable jar using Gradle:
>cd RegisterRestServiceGradle

>gradle clean assemble

### Run from executable jar (requires Java8):
>cd RegisterRestServiceGradle

>java -jar build/libs/RegisterRestServiceGradle-1.0-SNAPSHOT.jar

### Open H2 console to view all Registration records in browser, auto-loaded for testing (connect to jdbc:h2:mem:testdb):
>http://localhost:8080/console

### Get All Registrations:
>curl http://localhost:8080/registrations

### Get User Registration:
>curl http://localhost:8080/registrations/joe1

### Delete User Registration:
>curl -X "DELETE" http://localhost:8080/registrations/joe1


### Added2
### Add User Registration:
>curl -H "Content-Type: application/json" -X POST -d '{"userName":"xyz","email":"xyz@gmail.com"}' http://localhost:8080/registrations/add