# Journal Info System with Spring Boot and MongoDB

This project is a **Journal Information System** that enables users to securely log in, create journal entries, and manage them. The application uses **Spring Boot** for the backend, **MongoDB** for storing user and journal data, and **Spring Security** for implementing basic authentication.

## Features

- User Registration
- User Login with Basic Authentication
- Secure Journal Entry Creation
- User-specific journal management
- Stateless REST API
- handling of requests with MongoDB as a database
- REST API design following Spring Boot standards

## Technologies Used

- **Spring Boot** (v3.3.1)
- **Spring Security** (with Basic Authentication)
- **MongoDB** (Cloud or Local Database)
- **Spring Data MongoDB** for database interaction
- **Java 19** (or compatible version)
- **Maven** for dependency management

## Project Structure
src
│
├───main
│   ├───java
│   │   └───com
│   │       └───mohit
│   │           └───journalApp
│   │               ├───config        # Spring Security configuration
│   │               ├───controller    # REST API controllers
│   │               ├───entity       # User and Journal Entry entity
│   │               ├───repository    # MongoDB repositories
│   │               └───service       # Business logic and services
│   └───resources
│       ├───application.properties    # Application configuration
│       └───static                    # Static resources (if applicable)
├───test                              # Unit and integration tests
└───pom.xml                           # Maven dependencies and plugins

