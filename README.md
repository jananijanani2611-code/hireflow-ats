# HireFlow ATS

A full-stack Applicant Tracking System — Spring Boot + MySQL backend, React + Tailwind frontend.

## Structure

```
hireflow-ats/
├── backend/     Spring Boot 3 / Java 17 / MySQL / JWT auth
└── frontend/    React 18 / Vite / Tailwind CSS
```

## Quick Start (local development)

### 1. Backend

Requirements: Java 17+, Maven, MySQL running locally.

```bash
cd backend
```

Copy `.env.example` to `.env` (or export the variables directly) and set your own values:

```bash
DB_URL=jdbc:mysql://localhost:3306/hireflow_ats?createDatabaseIfNotExist=true
DB_USERNAME=root
DB_PASSWORD=your_local_mysql_password
JWT_SECRET=replace_with_a_long_random_256_bit_secret
CORS_ALLOWED_ORIGINS=http://localhost:5173
```

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
```

Create a `.env` file (see `.env.example`) with:

```bash
VITE_API_URL=http://localhost:8080/api
```

```bash
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

All endpoints except `POST /api/auth/**` and `GET /api/jobs/**` require an `Authorization: Bearer <token>` header (token returned from login/register).

## Tech Stack

**Backend:** Java 17, Spring Boot 3, Spring Security, Spring Data JPA, Hibernate, MySQL, JWT (jjwt), Lombok, Maven

**Frontend:** React 18, Vite, React Router, Tailwind CSS, Axios, Lucide Icons

## Deployment

- **Frontend** deploys as a static Vite build — Vercel, Netlify, or any static host. Set `VITE_API_URL` to your deployed backend's `/api` URL.
- **Backend** needs a host that runs a persistent JVM process plus a MySQL database — e.g. Render, Railway, Fly.io, or a VM. A `Dockerfile` and `render.yaml` are included in `backend/` to make this a few-click deploy on Render. Set the environment variables below on whichever host you use:

| Variable | Purpose |
|---|---|
| `DB_URL` | JDBC URL for the MySQL database |
| `DB_USERNAME` | MySQL username |
| `DB_PASSWORD` | MySQL password |
| `JWT_SECRET` | Random 256-bit+ secret used to sign JWTs |
| `CORS_ALLOWED_ORIGINS` | Comma-separated list of allowed frontend origins (e.g. your Vercel URL) |

## Notes for Git

Both `backend/.gitignore` and `frontend/.gitignore` are already set up to exclude `target/`, `node_modules/`, `dist/`, and IDE files — safe to `git init` and push as-is from the root folder.
