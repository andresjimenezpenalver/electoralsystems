
This is a maven multimodule project developed in Java for simulating electoral systems.
Currently only D'Hont Method is available.

# After cloning

You need Java 8+ and maven.

$ cd electoralsystems
electoralsystems $ mvn install


# Available applications


## Swing Application

In app module. For launch:

electoralsystems $ mvn -f app/pom.xml spring-boot:run


![alt text](https://raw.githubusercontent.com/andresjimenezpenalver/electoralsystems/master/app/src/site/images/swing-application.png)



## REST API Application 

In api module. For launch:

electoralsystems $ mvn -f api/pom.xml spring-boot:run 


URL swagger: http://localhost:8080/v2/api-docs 

URL swagger-ui: http://localhost:8080/swagger-ui.html

![alt text](https://raw.githubusercontent.com/andresjimenezpenalver/electoralsystems/master/api/src/site/images/swaggerui-restapi-application.png)
