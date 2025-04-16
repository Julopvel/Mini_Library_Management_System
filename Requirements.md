# Requirements:

## **Core Functionalities**

### 1. **Data model:**
- *Manage Books:*
  - id.
  - title.
  - author
  - ISBN.
  - Availability status.
   
- *Manage Users:*
  - id.
  - name.
  - email.
  - unique library ID.
   
- *Borrowing System:*
  - Allow users to borrow books (limit to 3 books per user).
  - Track due dates and borrowing history.
  - Implement logic to mark a book as unavailable when borrowed.

### 2. **Endpoints:**
- Get, Add, Update, Delete books and users.
   
### 3. **Search**:
- Implement search functionality for books by title, author, or ISBN.

### 4. **Authentication**
- Basic JWT-based user authentication (for Admin and User roles).
   
### 5. **Constraints**
- Books can only be borrowed if available.
- Users cannot borrow more than 2 books at a time.

### 6.  **Testing**
- Include unit tests and integration tests using **JUnit** and **Mockito**.
- Test REST endpoints with **MockMvc** or **Postman/Swagger** documentation.

### 7. **Documentation**
- Provide API documentation using **Swagger/OpenAPI**.
- Include a `README.md` with:
    - Instructions to build and run the project.
    - High-level architectural decisions.

---

### Optional:
- Implement caching using **Spring Cache** (e.g., caching popular book searches).
- Implement a scheduling feature using **Spring Scheduler** to notify users of overdue books.
- Use **Docker** to containerize the application.