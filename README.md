# Heroes Rest API

Features and implementation details:

* Heroes resource
    * GET -> all, byId and filter by name
    * POST
    * PUT
    * DELETE
    
* H2 Database
* Method executions timed with custom @Timed annotation
* ControllerAdviced configured for not found and bad request exceptions
* Integrations test were added
* Aplication Dockerized (mvn clean install & docker build)
* Cache support with Google Guava for get methods with 1 minute of TTL
* Documentation made in Swagger http://localhost:8080/swagger-ui.html
* Basic Authentication supported using in memory user manager- User and pass = admin
