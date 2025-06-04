# Mini_Library_Management_System

### Development

#### Prerequisites

Ensure that the PostgreSQL database is set up and running, and
that the [`database-schema`](database-schema) has been migrated.

#### Building and running with Maven

Build the project:

	mvn clean compile

Run the project:

	mvn clean spring-boot:run

Run the project with logs capturing:

	mvn clean spring-boot:run | tee `date +%y%m%d-%H%M`.log

#### Available API endpoints

Upon starting the project, the Swagger/OpenAPI documentation is available at http://localhost:8080/v3/api-docs or via the Swagger UI at http://localhost:8080/swagger-ui/index.html

