Meeting Management Server
==================
The backend server uses Spring Boot to support the functionalities including: 
* Create/Read/Update/Delete rooms
* Create/Read/Update/Delete users
* Create/Read/Update/Delete meetings
* Get weather info of the current location
* User management

Usage
============
To start the server, run the following command

```
//optional only run when maven wrapper is not set.
 mvn -N io.takari:maven:wrapper 
./mvnw spring-boot:run
```

The default port is `8080`. To customize the port, you can use `server.port={port_number}` in the [application.properties](../backend/src/main/resources/application.properties) file

all the requests expect get weather and create user requires JWT token in the header `x-authorization-token`. You will get the token once logged in. 