# 🏋️ Fitness Monolith – Backend Application

 A backend-focused RESTful application built using Spring Boot, Spring Data JPA, and MySQL.
 Designed with MVC architecture and a DTO layer to maintain clean separation between persistence and API contracts.
 Backend only — APIs tested using Postman.

## 🚀 Tech Stack

| Category | Technology |
|----------|------------|
| Programming Language | Java |
| Backend Framework | Spring Boot |
| ORM / Persistence | Spring Data JPA (Hibernate) |
| Database | MySQL |
| Architecture Pattern | MVC |
| Build & Dependency Tool | Maven |
| API Testing Tool | Postman |


## 🧱 Architecture Overview
Controller  →  Service  →  Repository  →  Database
                 ↓
               DTO Layer

## 🔹 Layers Explained

Controller: Handles HTTP requests and responses.
Service: Contains business logic.
Repository: Handles database operations.
DTO Layer: Separates entities from API contracts.

## 📌 Key Features

- Backend-only **RESTful APIs**
- Clean **MVC layered architecture**
- **DTO pattern** for safe and structured data transfer
- Database integration using **Spring Data JPA**
- **MySQL** relational database
- Proper use of **HTTP status codes**
- APIs tested using **Postman**


## 🗄️ Database
- Database: MySQ.
- ORM: Hibernate (via Spring Data JPA).
- Schema auto-managed using JPA annotations.

## 🔧 API Testing

All APIs are tested using Postman.
Standard HTTP methods used:
GET
POST
PUT
DELETE

## ⚙️ How to Run the Project

## 1️⃣ Clone the Repository

git clone https://github.com/avinash-2401/Fitness-Monolith.git

## 2️⃣ Configure Database

Update `application.properties`:

```properties
spring.datasource.url=jdbc:mysql://localhost:3306/fitness_db
spring.datasource.username=your_username
spring.datasource.password=your_password

spring.jpa.hibernate.ddl-auto=update
spring.jpa.show-sql=true

```
> Make sure MySQL is running before starting the application.


## 3️⃣ Run the Application
mvn spring-boot:run


The application will start on:

http://localhost:8080

## 📚 What I Learned

- Building real-world backend applications using **Spring Boot**
- Applying **MVC architecture** effectively
- Using **DTOs** to decouple API and database layers
- Working with **Spring Data JPA** and **MySQL**
- Designing and testing **RESTful APIs**


## 🔮 Future Enhancements

- Spring Security + JWT authentication
- Role-based authorization
- Swagger / OpenAPI documentation
- Pagination & sorting improvements


## 👨‍💻 Author

Avinash
-Java Backend Developer (Spring Boot)

🔗 GitHub Profile:
https://github.com/avinash-2401












