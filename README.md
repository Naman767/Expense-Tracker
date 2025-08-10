# Project Name:Expense-Tracker

A Spring Boot–based backend application designed to help users manage their personal income and expenses. It provides RESTful APIs for adding, updating, retrieving, and deleting financial records, along with category management and summary reports.

Backend: Java, Spring Boot, Spring Data JPA, Hibernate
Database: MySQL (or whichever DB you used)
Testing Tools: Postman (for API testing)
Build Tool: Maven/Gradle
Version Control: Git

**Key Features:**
Add, update, and delete income and expense records
Categorize transactions (e.g., Food, Travel, Salary, Bills)
View transactions sorted by date or category
Get summary reports (monthly/yearly)
Exception handling for invalid requests
API validation for required fields

**API Examples:**
POST /api/income → Add new income record
GET /api/income/all → Get all incomes
GET /api/income/{id} → Get income by ID
DELETE /api/income/{id} → Delete income
Similar endpoints for expense

**Testing with Postman:**
Verified CRUD operations for both income and expenses
Tested status codes (200 OK, 404 NOT FOUND, 500 INTERNAL SERVER ERROR)
Checked request/response JSON formats
Used query params and path variables for data retrieval
