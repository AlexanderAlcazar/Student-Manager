# Student-Manager

![Demo](https://img.shields.io/badge/Status-Active-brightgreen) ![Java](https://img.shields.io/badge/Java-20+-blue)

This repository contains a student management system built with Spring Boot, Spring Data JPA, and an embedded H2 database.

---

## Table of Contents
- [Project Overview](#project-overview)
- [Main Features](#main-features)
- [Technical Architecture](#technical-architecture)
- [Getting Started](#getting-started)
- [Educational Value](#educational-value)
- [Acknowledgments](#acknowledgments)

---

## Project Overview

The project is a Java-based student management platform built as an educational exercise. It features:

- **Spring Boot REST Backend:** Exposes HTTP endpoints for login and student management.
- **Real Database Persistence:** Student information is stored in H2 through Spring Data JPA.
- **DTO-based API:** Requests and responses use JSON-friendly data transfer objects.

---

## Main Features

- **User Authentication:** Supports login for students using the database-backed authentication flow.
- **Student Management:**
  - Add new students with personal details.
  - Remove students by ID.
  - List all students in the database.
  - Retrieve detailed information for a specific student.
- **Data Persistence:** All student records are stored in H2 and loaded automatically through JPA.
- **REST Communication:** Uses standard HTTP endpoints instead of a custom socket protocol.

---

## Technical Architecture

- **Database:**
  - Wraps Spring Data JPA and exposes CRUD-style operations for students.
  - Uses H2 as the runtime database.

- **Model:**
  - `Person` interface defines the contract for personal information.
  - `Student` is a JPA entity that stores student records in the database.

---

## Getting Started

1. **Clone the repository:**
   ```bash
   git clone https://github.com/AlexanderAlcazar/Student-Manager.git
   cd Student-Manager
   ```

2. **Build and Run:**
   - Ensure you have a compatible JDK and Maven installed.
   - Run the Spring Boot application from `StudentManagerApplication`.

3. **Usage:**
   - Use the REST API endpoints to add, remove, list, and fetch students.

---

## Educational Value

This project demonstrates core concepts in:

- Spring Boot and REST APIs
- Spring Data JPA and database persistence
- Object-oriented principles (interfaces, encapsulation, etc.)
- DTO-based API design

---

## Acknowledgments

- Special thanks to the group of students who contributed their time and expertise.
- Project was completed as part of the CS56 course curriculum.

---

*Built for educational purposes using Spring Boot, Spring Data JPA, and H2.*
