
This is a maven multimodule project developed in Java for simulating electoral systems.

Currently the following methods or algorithms are supported: 
- D'Hont Method
- Sainte-Laguë Method
- Droop Quota
- Hare Quota
- Imperiali Quota


## Building the applications

You need Java 8+ and maven.
Use the following command to build the applications:

```
$ cd electoralsystems

electoralsystems $ mvn install
```


## Launching the applications


### GUI/Swing Application

Use the following command to launch the GUI/Swing Application:

```
electoralsystems $ mvn -f app/pom.xml spring-boot:run
```


![alt text](https://raw.githubusercontent.com/andresjimenezpenalver/electoralsystems/master/app/src/site/images/swing-application.png)

Example of D'Hont Algorithm Results:

![alt text](https://raw.githubusercontent.com/andresjimenezpenalver/electoralsystems/master/app/src/site/images/swing-application-dhont-algorithm.png)


### REST API Application 

Use the following command to launch the API Application:

```
electoralsystems $ mvn -f api/pom.xml spring-boot:run 
```


URL swagger: http://localhost:8080/v2/api-docs 

URL swagger-ui: http://localhost:8080/swagger-ui.html

![alt text](https://raw.githubusercontent.com/andresjimenezpenalver/electoralsystems/master/api/src/site/images/swaggerui-restapi-application.png)
