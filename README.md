✈️ Flight Reservation REST API

Spring Boot REST API for managing flight bookings with comprehensive reservation features and user management.

 🌟 Features:
 
- 🔐 User Management
  - User registration and profile updates
  - Role-based access 
  - Conflict handling for duplicate emails/usernames

- ✈️ Flight Operations
  - Flight creation and management
  - Available seats tracking
  - Conflict prevention for duplicate flights

- 📅 Booking System
  - Seat reservation functionality
  - User-flight relationship management
  - Detailed booking retrieval

 🛠️ Tech Stack

- Backend: Spring Boot, Spring Data JPA
- Database: (MySQL)
- Data Mapping: MapStruct
- Error Handling: Custom exceptions (404/409)
- Testing: JUnit, Mockito 
  
 📡 API Endpoints

 👥 User Endpoints
`POST /api/users`  
Create new user  

`GET /api/users/{userId}`  
Get user by ID  

`PUT /api/users/{userId}`  
Update user  

`GET /api/users`  
Get all users  

 ✈️ Flight Endpoints
`POST /api/flights`  
Create new flight  

`GET /api/flights/{flightId}`  
Get flight details  

 📅 Booking Endpoints
`POST /api/bookings`  
Create new booking  

`GET /api/bookings/{bookingId}`  
Get booking details  
