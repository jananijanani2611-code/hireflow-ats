# HireFlow ATS — Frontend

React + Vite + Tailwind CSS frontend for HireFlow ATS.

## Setup

```bash
npm install
npm run dev
```

Runs on http://localhost:5173 by default and talks to the backend at http://localhost:8080.

## Build

```bash
npm run build
```

## Structure

- `src/api/client.js` — Axios instance with JWT interceptor
- `src/context/AuthContext.jsx` — Login/register/logout state, persisted to localStorage
- `src/components/` — Layout (sidebar nav), ProtectedRoute, Modal, StatusBadge
- `src/pages/` — Login, Register, Dashboard, Candidates, Jobs, Applications

## Notes

- Make sure the backend is running on port 8080 before starting the frontend (or update `baseURL` in `src/api/client.js`).
- First-time use: register an account via `/register`, then log in.
