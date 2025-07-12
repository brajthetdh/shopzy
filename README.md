
# 🛒 E-Commerce Platform – Spring Boot + React + Kafka

An Amazon-like full-stack e-commerce platform built from scratch using:
- Backend: Java, Spring Boot, Spring Security, JPA
- Frontend: React.js (Upcoming)
- Messaging: Kafka (for async notifications and order events)

---

## ✅ Current Progress

**Phase 1: Authentication & Setup**
- [x] Spring Boot backend setup
- [x] PostgreSQL DB config
- [x] JWT-based authentication
- [x] Role-based access: `ADMIN`, `SELLER`, `CUSTOMER`
- [ ] Kafka integration (in progress)
- [ ] React frontend scaffolding (upcoming)

---

## 🧱 Tech Stack

| Layer     | Tech                          |
|-----------|-------------------------------|
| Backend   | Spring Boot, Spring Security, JPA, JWT |
| Frontend  | React.js (with TailwindCSS)   |
| Database  | PostgreSQL                    |
| Messaging | Apache Kafka                  |
| Auth      | JWT Token                     |
| DevOps    | Docker (planned)              |

---

## 📁 Project Structure (Backend)

ecommerce-backend/
├── auth/                # JWT login & signup
├── user/                # User profile and roles
├── config/              # Security and JWT filters
├── kafka/               # Kafka producer/consumer (upcoming)
├── common/              # DTOs, constants, exception handling

---

## 🔐 Authentication API

**Register**
POST /auth/register
Request Body:
{
  "username": "john",
  "email": "john@example.com",
  "password": "password",
  "role": "CUSTOMER"
}

**Login**
POST /auth/login
Request Body:
{
  "username": "john",
  "password": "password"
}

Returns JWT token:
Authorization: Bearer <token>

---

## 🚀 How to Run

1. Clone the repo
2. Setup PostgreSQL and configure `application.yml`
3. Run the Spring Boot app

Command:
./mvnw spring-boot:run

---

## 🧭 Upcoming Modules

- [ ] Kafka config for async events
- [ ] Product catalog module
- [ ] React frontend setup
- [ ] Cart and checkout
- [ ] Payment gateway
- [ ] Admin dashboard
- [ ] Dockerization
