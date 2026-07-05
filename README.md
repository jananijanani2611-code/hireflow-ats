<<<<<<< HEAD
# HireFlow ATS

A full-stack Applicant Tracking System — Spring Boot + MySQL backend, React + Tailwind frontend.

## Structure

```
hireflow-ats/
├── backend/     Spring Boot 3 / Java 17 / MySQL / JWT auth
└── frontend/    React 18 / Vite / Tailwind CSS
```

## Quick Start

### 1. Backend

Requirements: Java 17+, Maven, MySQL running locally.

```bash
cd backend
```

Edit `src/main/resources/application.properties` if your MySQL username/password differ from `root`/`root`. The database `hireflow_ats` is created automatically on first run.

```bash
./mvnw spring-boot:run       # Mac/Linux
mvnw.cmd spring-boot:run     # Windows
```

Runs on **http://localhost:8080**. Tables are auto-created by Hibernate (`ddl-auto=update`).

### 2. Frontend

Requirements: Node 18+.

```bash
cd frontend
npm install
npm run dev
```

Runs on **http://localhost:5173**.

### 3. First use

1. Open http://localhost:5173/register and create an account (choose role: ADMIN, RECRUITER, or CANDIDATE).
2. You'll be logged in automatically and redirected to the dashboard.
3. Recruiters/Admins can post jobs and manage candidates; anyone logged in can view jobs and applications.

## API Overview

| Resource | Endpoints |
|---|---|
| Auth | `POST /api/auth/register`, `POST /api/auth/login` |
| Candidates | `GET/POST /api/candidates`, `GET/PUT/DELETE /api/candidates/{id}` |
| Jobs | `GET/POST /api/jobs`, `GET/PUT/DELETE /api/jobs/{id}`, `GET /api/jobs/status/{status}` |
| Applications | `GET/POST /api/applications`, `GET /api/applications/candidate/{id}`, `GET /api/applications/job/{id}`, `PATCH /api/applications/{id}/status`, `DELETE /api/applications/{id}` |
| Dashboard | `GET /api/dashboard` |

All endpoints except `POST /api/auth/**` and `GET /api/jobs/**` require a `Authorization: Bearer <token>` header (token returned from login/register).

## Tech Stack

**Backend:** Java 17, Spring Boot 3, Spring Security, Spring Data JPA, Hibernate, MySQL, JWT (jjwt), Lombok, Maven

**Frontend:** React 18, Vite, React Router, Tailwind CSS, Axios, Lucide Icons

## Notes for Git

Both `backend/.gitignore` and `frontend/.gitignore` are already set up to exclude `target/`, `node_modules/`, `dist/`, and IDE files — safe to `git init` and push as-is from the root folder.
=======
# hireflow-ats
>>>>>>> b8ec808cbd2583c8555ca39f7490199a02129f8a
