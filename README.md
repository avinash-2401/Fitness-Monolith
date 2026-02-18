# 🏋️ **Fitness Monolith – Secure Backend Application**

A backend-focused RESTful application built using **Spring Boot**, **Spring Security**, **JWT Authentication**, **Swagger OpenAPI**, **Spring Data JPA**, and **MySQL**.
Designed with MVC architecture and a DTO layer to maintain clean separation between persistence and API contracts.
Backend-only system — APIs can be tested using **Swagger UI** or **Postman**.

---

# 🚀 Tech Stack

| Category             | Technology                    |
| -------------------- | ----------------------------- |
| Programming Language | Java                          |
| Backend Framework    | Spring Boot                   |
| Security             | Spring Security + JWT         |
| API Documentation    | Swagger / OpenAPI (springdoc) |
| ORM / Persistence    | Spring Data JPA (Hibernate)   |
| Database             | MySQL                         |
| Architecture Pattern | MVC + DTO Layer               |
| Build Tool           | Maven                         |
| API Testing          | Swagger UI, Postman           |

---

# 🔐 Security Overview (NEW)

The project now implements **stateless JWT-based authentication**.

### ✅ Authentication Flow

1. User logs in via authentication API.
2. Server generates a JWT token.
3. Client sends token in headers:

```
Authorization: Bearer <token>
```

4. `JwtAuthFilter` validates requests before accessing secured APIs.

### 🔓 Public Endpoints

```
/api/auth/**
/swagger-ui/**
/v3/api-docs/**
```

### 🔒 Secured Endpoints

```
/api/admin/** → ROLE_ADMIN
Other APIs → Authenticated Users
```

Session management is **STATELESS**, and CSRF is disabled for REST APIs.

---

# 📘 Swagger API Documentation (NEW)

Swagger UI is integrated for interactive API testing.

### Open Swagger UI:

```
http://localhost:8080/swagger-ui/index.html
```

### OpenAPI JSON:

```
http://localhost:8080/v3/api-docs
```

Swagger endpoints are excluded from security filters for easier testing.

---

# 🧱 Architecture Overview

```
Controller → Service → Repository → Database
                ↓
             DTO Layer
```

## 🔹 Layers Explained

**Controller**

* Handles HTTP requests & responses

**Service**

* Business logic & validations

**Repository**

* Database access using JPA

**DTO Layer**

* Separates Entities from API contracts
* Prevents direct exposure of persistence models

**Security Layer (NEW)**

* JwtAuthFilter
* SecurityConfig
* Role-based authorization

---

# 📌 Key Features

* Backend-only RESTful APIs
* JWT Authentication & Authorization
* Role-based access control
* Swagger API documentation
* Clean MVC layered architecture
* DTO pattern for structured data transfer
* MySQL integration with Spring Data JPA
* Proper HTTP status handling
* Stateless secure API design

---

# 🗄️ Database

* Database: MySQL
* ORM: Hibernate (Spring Data JPA)
* Schema managed via JPA annotations

Example configuration:

```
spring.datasource.url=jdbc:mysql://localhost:3306/fitness_db
spring.datasource.username=your_username
spring.datasource.password=your_password

spring.jpa.hibernate.ddl-auto=update
spring.jpa.show-sql=true
```

---

# 🔧 API Testing

You can test APIs using:

✅ Swagger UI (recommended)
✅ Postman

Supported HTTP Methods:

```
GET
POST
PUT
DELETE
```

---

# ⚙️ How to Run the Project

## 1️⃣ Clone Repository

```
git clone https://github.com/avinash-2401/Fitness-Monolith.git
```

## 2️⃣ Configure Database

Update `application.properties` with your MySQL credentials.

Ensure MySQL is running before starting the app.

## 3️⃣ Run Application

```
mvn spring-boot:run
```

Application runs at:

```
http://localhost:8080
```

---

# 📂 Project Structure

```
com.project.Fitness
 ├── Controller
 ├── Service
 ├── Repository
 ├── Entity
 ├── DTO
 └── Security
       ├── SecurityConfig
       ├── JwtAuthFilter
       └── JwtService
```

---

# 📚 What I Learned

* Building secure backend systems using Spring Boot
* Implementing JWT authentication with Spring Security
* Designing layered MVC architecture
* Using DTOs to decouple API and database models
* Integrating Swagger for API documentation
* Creating stateless REST APIs

---

# 🔮 Future Enhancements

* Refresh Token mechanism
* API versioning
* Global exception handling improvements
* Pagination & sorting optimization
* Production security hardening

---

# 👨‍💻 Author

**Avinash** — Java Backend Developer (Spring Boot)

🔗 GitHub Profile: https://github.com/avinash-2401
