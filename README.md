# Employee Management REST API

A RESTful Employee Management application developed using Java and Spring Boot, with MySQL for data persistence.

## Technologies Used
- Java 21
- Spring Boot
- Spring Data JPA and Hibernate
- MySQL
- Maven
- Swagger / OpenAPI

## Features
- Create, retrieve, update, and delete employees
- Search employees by name
- Filter employees by status
- Pagination support
- Input validation
- API documentation through Swagger UI

## API Endpoints

| Method | Endpoint | Description |
|---|---|---|
| POST | `/api/employees` | Create an employee |
| GET | `/api/employees` | List employees, with optional filters and pagination |
| GET | `/api/employees/{id}` | Get an employee by ID |
| PUT | `/api/employees/{id}` | Update an employee |
| DELETE | `/api/employees/{id}` | Delete an employee |

## Setup and Run

1. Install JDK 21 and MySQL.
2. Create a MySQL database named `testdb`.
3. Configure your database connection and password using local environment variables.
4. Run the application using the Maven wrapper:

   ```bash
   ./mvnw spring-boot:run
   ```

   On Windows, use:

   ```bat
   mvnw.cmd spring-boot:run
   ```

5. Open Swagger UI at `http://localhost:8080/swagger-ui/index.html`.

**Security:** Do not commit real database passwords, API keys, or other secrets to the repository.

*Note: Status can be either `Active` or `Inactive`. It is case sensitive.* 
