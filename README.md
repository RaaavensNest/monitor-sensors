# 📡 Monitor Sensors

A Spring Boot service for managing sensor entities with CRUD operations, search, validation, and secure access using JWT authentication and role-based authorization.

This service integrates with a secondary microservice — [`sensor-statistics`](https://github.com/JustAnAverageMax/sensor-statistics), which collects and stores sensor statistics daily.

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

2. Start the application using Docker Compose:

```bash
docker-compose -f ./docker-compose.yaml up --build
```

This will:

- Build the `monitor-sensors` service from source
- Pull the latest Docker image of `sensor-statistics`
- Start two PostgreSQL databases (one per service)
- Enable communication between both services

---

## 🧪 API Access

| Service               | Base URL                       | Swagger UI                                         |
|-----------------------|--------------------------------|----------------------------------------------------|
| **Monitor Sensors**   | [http://localhost:18080](http://localhost:18080)         | [http://localhost:18080/swagger-ui/index.html](http://localhost:18080/swagger-ui/index.html) |
| **Sensor Statistics** | [http://localhost:18081](http://localhost:18081)         | [http://localhost:18081/swagger-ui/index.html](http://localhost:18081/swagger-ui/index.html) |

---
## 🔐 Predefined Users

| Role          | Email               | Password       |
|---------------|---------------------|----------------|
| ADMINISTRATOR | `admin@example.com` | `Password123!` |
| VIEWER        | `j.doe@example.com` | `Password123!` |

---

## 🔍 Features

### Monitor Sensors

- JWT authentication and RBAC
- CRUD operations for sensor entities
- Search by name/model (case-insensitive, partial match)
- Pagination and sorting
- Swagger/OpenAPI 3 documentation
- Liquibase schema migrations
- Centralized error handling

### Sensor Statistics

- Uses Feign client to fetch all sensors from `monitor-sensors`
- Has a scheduled task at 02:00 daily to aggregate and store statistics:
  - Total sensors count
  - Count per sensor type
- Can filter statistics by date range with pagination
- Exposed endpoint:

  ```
  GET /api/v1/statistics?startDate=yyyy-MM-dd&endDate=yyyy-MM-dd&page=0&size=10
  ```

---

## ⚙️ Environment Variables

Override in `docker-compose.yaml` or `.env`:

| Variable                     | Description                                                                 | Default Value                                                |
|-----------------------------|-----------------------------------------------------------------------------|--------------------------------------------------------------|
| `SPRING_DATASOURCE_URL`     | PostgreSQL JDBC URL                                                         | `jdbc:postgresql://postgres-monitor-sensors:5432/sensors_db` |
| `SPRING_DATASOURCE_USERNAME`| PostgreSQL user                                                             | `postgres`                                                   |
| `SPRING_DATASOURCE_PASSWORD`| PostgreSQL password                                                         | `postgres`                                                   |
| `JWT_SECRET`                | JWT signing key (min 256-bit)                                               | `DtLln0ulGdXd9LO9U3BEgXRV6tgloi2g`                           |
| `INTERNAL_API_KEY`          | Used by `sensor-statistics` to access internal endpoints securely          | `7F01A8F1234567890123456789ABCDEF`                           |
| `MONITOR_SENSORS_URL`       | (for `sensor-statistics`) base URL of monitor service                      | `http://monitor-sensors-service:8080`                        |

---

## 🔐 JWT Authentication

1. Authenticate via `/login`
2. Copy `accessToken`
3. Click "Authorize" in Swagger UI and paste your token

---

## 🛠 Tech Stack

- Java 21
- Spring Boot 3.5
- Spring Security + JWT
- Spring Data JPA + PostgreSQL
- Liquibase
- OpenAPI 3 (Swagger)
- Feign Client
- Docker & Docker Compose

---

## 👤 Author

**Maksim Krutalevich**  
GitHub: [https://github.com/justanaveragemax](https://github.com/justanaveragemax)

---

## 📄 License

This project is licensed under the MIT License.  
Created as part of a technical assignment.
