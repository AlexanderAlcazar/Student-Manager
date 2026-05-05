# Spring Boot Backend Migration Proposal

## Purpose
This document proposes how to replace the current custom socket-based backend with a Spring Boot REST backend.

The goal is to make the server easier to read, maintain, test, and extend while keeping the existing student-management behavior intact.

---

## Current Backend Summary

The current backend is implemented in `src/main/java/edu/smc/network/Server.java` and works as a blocking TCP server:

- Opens a `ServerSocket` on port `5000`
- Accepts one client at a time
- Reads commands as plain text lines
- Uses `#`-delimited strings for command parsing
- Returns plain text responses such as `true`, `false`, or student data
- Persists data through `Database`

Example command flow:

```text
login#username#password#student
add#first#last#123#555-1111#Main Street
remove#123
list
info#123
```

This works, but the protocol is custom and requires manual parsing in the server.

---

## Proposed Target Architecture

The backend should be reorganized into a Spring Boot application with the following layers:

### 1. Application entry point
A single Spring Boot launcher class, for example:

- `StudentManagerApplication.java`

This replaces the current manual server startup logic.

### 2. Controllers
REST controllers should expose HTTP endpoints for login and student operations.

Examples:

- `AuthController`
- `StudentController`

### 3. Services
Business logic should move into service classes.

Examples:

- `AuthService`
- `StudentService`

These classes should contain the logic currently spread across `processLoginCommand`, `processAddCommand`, `processRemoveCommand`, and `processInfoCommand`.

### 4. Persistence layer
The current `Database` class can initially become the persistence layer or be wrapped by one.

This layer should handle:

- loading data
- saving data
- finding students
- verifying credentials

### 5. DTOs
Instead of parsing raw text, HTTP requests should use JSON request objects.

Examples:

- `LoginRequest`
- `StudentRequest`
- `StudentResponse`

---

## Command-to-Endpoint Mapping

| Current Command | Proposed REST Endpoint | HTTP Method | Purpose |
|---|---|---:|---|
| `login` | `/api/auth/login` | `POST` | Verify admin or student login |
| `add` | `/api/students` | `POST` | Create a new student |
| `remove` | `/api/students/{id}` | `DELETE` | Remove a student by ID |
| `list` | `/api/students` | `GET` | Return all students |
| `info` | `/api/students/{id}` | `GET` | Return one student’s details |

---

## Example Request Shapes

### Login

```json
{
  "username": "admin",
  "password": "admin",
  "role": "admin"
}
```

### Add student

```json
{
  "firstName": "John",
  "lastName": "Doe",
  "studentId": 123,
  "phoneNumber": "555-1111",
  "address": "Main Street",
  "major": "Computer Science"
}
```

### Student response

```json
{
  "firstName": "John",
  "lastName": "Doe",
  "studentId": 123,
  "phoneNumber": "555-1111",
  "address": "Main Street",
  "major": "Computer Science"
}
```

---

## Package Structure Proposal

A clean Spring Boot layout could look like this:

```text
src/main/java/edu/smc/
  StudentManagerApplication.java
  controller/
    AuthController.java
    StudentController.java
  service/
    AuthService.java
    StudentService.java
  repository/
    DatabaseRepository.java
  dto/
    LoginRequest.java
    StudentRequest.java
    StudentResponse.java
  model/
    Student.java
    Administrator.java
    Major.java
```

---

## Migration Phases

### Phase 1: Establish Spring Boot project structure
- Add Spring Boot dependencies
- Create the main application class
- Confirm the backend starts successfully

### Phase 2: Move business logic into services
- Extract login, add, remove, list, and info behavior from `Server.java`
- Keep file persistence working through the existing data layer

### Phase 3: Expose REST endpoints
- Implement controllers for login and student CRUD
- Replace `#`-delimited message parsing with JSON requests

### Phase 4: Update client communication
- Replace the socket client with an HTTP client in the JavaFX app
- Send JSON requests to the REST API

### Phase 5: Clean up legacy networking code
- Remove `Server.java` and `Client.java` socket logic if the HTTP version fully replaces it
- Keep only the parts that are still useful

---

## Compatibility Notes

The existing JavaFX client currently depends on socket communication. If the backend changes to HTTP, the client must also be updated.

Two possible transition strategies:

1. **Full cutover**
   - Replace server and client communication together
   - Simpler long-term, but larger change

2. **Gradual migration**
   - Keep the old socket backend temporarily
   - Introduce Spring Boot alongside it
   - Switch the client later

---

## Risks and Open Questions

- Should persistence remain file-based or move to a real database?
- Should the JavaFX client be updated immediately or later?
- Should the old socket protocol be preserved temporarily?
- Do login responses need to stay as plain strings, or should they become structured HTTP responses?

---

## Recommendation

For this project, the most practical path is:

- keep the existing model classes
- move logic into Spring Boot services and controllers
- keep file-based persistence for the first migration step
- update the JavaFX client after the REST backend is stable

This keeps the rewrite manageable while still improving the architecture significantly.

