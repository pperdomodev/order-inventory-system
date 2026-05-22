# Order Inventory System

Enterprise backend system for order and inventory management developed with Spring Boot following Hexagonal Architecture, DDD, SOLID principles and Event Driven Architecture.

---

# Project Overview

This project was designed to simulate a real enterprise-grade backend system capable of handling:

- Order management
- Inventory reservation
- Concurrent transactions
- State machine workflows
- Asynchronous event processing
- Role-based security
- Distributed architecture patterns
- High concurrency scenarios

The system supports transactional consistency, optimistic locking, caching, retries, asynchronous messaging and advanced API search capabilities.

---

# Architecture

The project follows:

- Hexagonal Architecture (Ports & Adapters)
- Domain Driven Design (DDD)
- SOLID Principles
- Event Driven Architecture
- Layered Architecture

---

# Main Features

## Authentication & Security

- JWT Stateless Authentication
- Role Based Access Control (RBAC)
- Spring Security
- Method Security
- Security Headers
- Audit Logging
- Protected Endpoints

---

## Orders Module

- Create orders
- Update order status
- Order lifecycle state machine
- Order history tracking
- Pagination
- Advanced filtering
- API Versioning
- Transaction management

---

## Inventory Module

- Inventory reservation
- Inventory release
- Concurrent stock handling
- Optimistic Locking with `@Version`
- Race condition prevention
- Rollback consistency

---

## Messaging & Async Processing

- RabbitMQ integration
- Event publishing
- Async notifications
- Retry handling
- Dead Letter Queue (DLQ)
- Event-driven communication

---

## Cache

- Redis cache integration
- Cached order search
- High performance query optimization

---

## Testing

- Unit Tests
- Integration Tests
- Concurrency Tests
- JMeter Load Testing
- JaCoCo Coverage (80% minimum)

---

# Technologies

| Technology | Version |
|---|---|
| Java | 21 |
| Spring Boot | 3.3.5 |
| Spring Security | Latest |
| MySQL | 8 |
| Redis | Latest |
| RabbitMQ | 3 |
| JWT | 0.11.5 |
| Swagger OpenAPI | 2.5.0 |
| JUnit 5 | Latest |
| Mockito | Latest |
| JMeter | Latest |
| Maven | Latest |

---

# Architectural Patterns Implemented

- Hexagonal Architecture
- Repository Pattern
- Adapter Pattern
- State Machine Pattern
- Event Driven Pattern
- Dependency Injection
- Specification Pattern
- Retry Pattern
- CQRS-inspired Query Separation

---

# Package Structure

```txt
src/main/java
│
├── application
│   ├── dto
│   └── service
│
├── domain
│   ├── model
│   ├── ports
│   ├── event
│   └── exception
│
├── infrastructure
│   ├── config
│   ├── entity
│   └── adapters
│       ├── input
│       └── output
│
└── shared
    └── response