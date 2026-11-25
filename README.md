# Dynamic Form Management System

A beginner-friendly full-stack mini project for creating dynamic forms, validating submissions, and displaying role-based dashboards.

## Tech stack
- Frontend: React, Vite, JavaScript, Bootstrap, Axios, React Router, React Hook Form
- Backend: Java 17, Spring Boot 3, Spring Data JPA, Spring Validation
- Database: MySQL

## Project structure
```text
Dynamic-Form-Management-System/
├── frontend/
└── backend/
```

## 1. Database
Create a MySQL database:
```sql
CREATE DATABASE dynamic_form_db;
```
Update `backend/src/main/resources/application.properties` with your MySQL username and password.

## 2. Backend
Open the `backend` folder in your Java IDE. Run `DynamicFormApplication.java`.
The API runs at `http://localhost:8080`.

Default demo accounts are created automatically:
- Admin: `admin@example.com` / `Admin@123`
- User: `user@example.com` / `User@123`

These are demo credentials for local practice. Change them before deploying.

## 3. Frontend
Open the `frontend` folder in VS Code. Install dependencies with `npm install`, then start the Vite development server with `npm run dev`.
The frontend runs at `http://localhost:5173`.

## Features
- Register and log in
- Admin and User role-based views
- Admin can create, edit, publish and delete forms
- Dynamic field types: text, email, number, date, dropdown, checkbox and textarea
- React Hook Form validation and controlled inputs
- User can view published forms and submit responses
- Admin can view submitted responses
- Axios API integration and protected routes
- Backend validation and global exception handling

## Notes
- This is an educational starter project. Authentication uses a simple bearer token stored in browser local storage; it is not production-grade JWT security.
- For production, use Spring Security with JWT, password hashing, HTTPS, stronger authorization checks and environment-based secrets.
