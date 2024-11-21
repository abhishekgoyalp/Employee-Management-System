# Employee Management System

A Spring Boot application for managing employees with polymorphic entities like `PermanentEmployee` and `ContractEmployee`. The application demonstrates CRUD operations, utilizes Hibernate/JPA for database interactions, and follows industry-standard coding practices.

## Getting Started

### Prerequisites
Ensure you have the following installed:
- **Java JDK**: Version 17 or higher.
- **Maven**: Dependency and build management.
- **MySQL**: Relational database.

### 1. Clone the Repository
```bash
git clone https://github.com/abhishekgoyalp/Employee-Management-System.git
cd Employee-Management-System
```

### 2. Configure the Database
Open the `src/main/resources/application.properties` file and update it with your database configuration.

#### MySQL Configuration:
```properties
spring.datasource.url=jdbc:mysql://localhost:3306/your_db
spring.datasource.username=your_db_username
spring.datasource.password=your_db_password

spring.jpa.hibernate.ddl-auto=update
spring.jpa.show-sql=true
```

### 3. Build the Project
Run the following command to build the project:
```bash
mvn clean install
```

### 4. Run the Application
By default, It will run on localhost with port 8080. If you want to run it on another port just add one property(`server.port=your_port`) in `application.properties` file. Start the application with:
```bash
mvn spring-boot:run
```

### 5. Running Tests
Run unit tests with:
```bash
mvn test
```

## API Endpoints
| Method | Endpoint               | Description                         |
|--------|------------------------|-------------------------------------|
| POST   | `/api/employees`       | Create a new employee              |
| GET    | `/api/employees/{id}`  | Get an employee by ID              |
| PUT    | `/api/employees/{id}`  | Update an existing employee        |
| DELETE | `/api/employees/{id}`  | Delete an employee                 |
| GET    | `/api/employees`       | Get all employees                  |

## Features
- **CRUD Operations**: Create, Read, Update, and Delete employees.
- **Polymorphism**: Handle multiple employee types (`PermanentEmployee` and `ContractEmployee`).
- **Database Interaction**: Uses PostgreSQL (or MySQL) with Hibernate ORM.
- **Unit Tests**: JUnit-based test cases for validation and business logic.
