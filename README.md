# 📡 Monitor Sensors

A Spring Boot service for managing sensor entities with CRUD operations, search, validation, and secure access using JWT authentication and role-based authorization.

---

## 🚀 Quick Start with Docker

### 📦 Requirements

- [Git](https://git-scm.com/)
- [Docker](https://www.docker.com/)
- [Docker Compose](https://docs.docker.com/compose/install/)

### ▶️ Run the Application

1. Clone the repository:

```bash
git clone https://github.com/JustAnAverageMax/monitor-sensors.git
cd monitor-sensors
```

2. Start the application using Docker:

```bash
docker-compose -f ./docker-compose.yaml up --build
```

> 📝 This will build the application from source and start both the Spring Boot service and PostgreSQL database.

Once started:

- API will be available at: `http://localhost:18080`
- Swagger UI: [http://localhost:18080/swagger-ui/index.html](http://localhost:18080/swagger-ui/index.html)

---

## 🔐 Predefined Users

The system initializes two users with different roles:

| Role          | Email               | Password       |
|---------------|---------------------|----------------|
| ADMINISTRATOR | `admin@example.com` | `Password123!` |
| VIEWER        | `j.doe@example.com` | `Password123!` |

---

## 🔍 Features

- JWT-based authentication and role-based access control
- Sensor entity management (CRUD)
    - Only `ADMINISTRATOR` can create, update, or delete
    - `VIEWER` can only list and search
- Search by `name` and `model` (case-insensitive, partial match)
- Pagination and sorting
- OpenAPI 3 / Swagger documentation
- Liquibase for schema migrations
- Spring validation and custom error model

---

## 🛠 Environment Variables

You can override the defaults in `docker-compose.yaml` or using a `.env` file:

| Variable                     | Description                                                                          | Default Value                                                |
|-----------------------------|--------------------------------------------------------------------------------------|--------------------------------------------------------------|
| `SPRING_DATASOURCE_URL`     | PostgreSQL JDBC URL                                                                  | `jdbc:postgresql://postgres-monitor-sensors:5432/sensors_db` |
| `SPRING_DATASOURCE_USERNAME`| PostgreSQL user                                                                      | `postgres`                                                   |
| `SPRING_DATASOURCE_PASSWORD`| PostgreSQL password                                                                  | `postgres`                                                   |
| `JWT_SECRET`                | Secret key used for signing JWTs. Make sure your custom key is 256bit length or more | `DtLln0ulGdXd9LO9U3BEgXRV6tgloi2g`                             |

---

## 📂 Project Structure

```
.
├── src/                              # Source code
├── resources/db.changelog/migrations # Database migrations
├── Dockerfile                        # App build instructions
├── docker-compose.yaml               # Docker orchestration file
├── README.md                         # This file
```

---

## 📄 API Documentation

After running the project, access Swagger UI at:

📍 [`http://localhost:18080/swagger-ui/index.html`](http://localhost:18080/swagger-ui/index.html)

From there, you can:
- Explore available endpoints
- Authenticate using JWT Bearer tokens
- Test API interactions directly

---

## 🔐 JWT Authentication

After a successful login (via the `/login` endpoint), you will receive an `accessToken` and `refreshToken`.

To authenticate requests in Swagger UI:
1. Click the "Authorize" button.
2. Enter the access token and click "Authorize"

---

## ⚙️ Technologies Used

- Java 21
- Spring Boot 3.5.0
- Spring Security + JWT
- Spring Data JPA + Hibernate
- PostgreSQL
- Liquibase
- Swagger / OpenAPI 3
- Docker / Docker Compose

---

## 👤 Author

**Maksim Krutalevich**  
GitHub: [https://github.com/justanaveragemax](https://github.com/justanaveragemax)

---

## 📄 License

This project is licensed under the MIT License.  
Created as part of a technical assignment.
