
# Computer Lab Management

Both client side and server side Java Spring Boot Web application to manage a computer lab.

## Technologies
- Java version: 17
- Spring Boot 3
- PostgreSQL
- Gradle
- Spring Security (JWT is used)

## Features

- Authentication and authorization are supported (2 Role: Admin, User)
- Adding, reserving, deleting operations for computers and users
- User image uploading
- Register and Login operations

## Dependencies
- org.springframework.boot:spring-boot-starter-security
- org.springframework.boot:spring-boot-starter-thymeleaf
- org.springframework.boot:spring-boot-starter-web
- org.thymeleaf.extras:thymeleaf-extras-springsecurity6
- org.springframework.boot:spring-boot-starter-data-jpa
- io.jsonwebtoken:jjwt-api:0.11.2
- io.jsonwebtoken:jjwt-impl:0.11.2
- io.jsonwebtoken:jjwt-jackson:0.11.2
- org.projectlombok:lombok
- org.springframework.boot:spring-boot-starter-test
- org.springframework.security:spring-security-test

## Usage
- Ensure that Java 17 and gradle are installed. 
```
    java -version
    gradle -v
```
- Clone the repository and navigate the location.
```
    cd /path/to/your/project
``` 
```
    gradlew run
```
## Licence

[MIT](https://choosealicense.com/licenses/mit/)

