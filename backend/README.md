# 📱 Spring Social Media API

A backend RESTful API built with Spring Boot for a social media application. It supports user registration, login, and CRUD operations on messages (posts).

---

## 🚀 Features

- ✅ User Registration & Login (with basic authentication)
- ✅ Create, Read, Update, Delete (CRUD) messages
- ✅ Get all messages or message by ID
- ✅ Exception handling with meaningful responses
- ✅ RESTful API structure using Spring MVC and Spring Data JPA
- ✅ PostgreSQL database support

---

## 🛠️ Tech Stack

- Java 21
- Spring Boot
- Spring MVC
- Spring Data JPA
- PostgreSQL
- Maven
- Lombok
- Docker

---

## 🌐 Live Demo

The API is deployed and accessible at: https://demo-deployment-latest-mt9h.onrender.com

---

## 🐳 Deployment

This application is containerized using Docker and deployed on Render.

## Docker Configuration

The project includes a Dockerfile for containerization. To build and run locally:

```bash
# Build the Docker image
docker build -t your-project-name .

# Login to Docker Hub
docker login

#Push the Image
docker push your-username/project-name:latest
```

## Render Deployment

The application is deployed on Render using a pre-built Docker image from Docker Hub.

Prerequisites

- Docker Desktop installed and running
- Docker Hub account

Steps to Deploy on Render

- Create a new Web Service on Render
- Choose "Deploy an existing image from a registry"
- Enter your Docker Hub image URL: your-dockerhub-username/ your-project-name
- Configure environment variables
<!-- ## 📂 Project Structure

src/
├── main/
│ ├── java/com/example/socialmedia/
│ │ ├── controller/
│ │ ├── service/
│ │ ├── repository/
│ │ ├── model/
│ │ └── exception/
│ └── resources/
│ ├── application.properties
├── test/

--- -->

## 🔧 Setup Instructions

1. **Clone the repository**

```bash
git clone https://github.com/iamsharanshetty/Social-Feed-Api.git
cd Social-Feed-Api
```

2. **Configure the database**

Configure PostgreSQL in application.properties:

```bash
spring.application.name=socials
spring.datasource.driver-class-name = org.postgresql.Driver
spring.datasource.url = jdbc:postgresql://localhost:5432/social_app_db
spring.datasource.username = postgres
spring.datasource.password = ********
spring.jpa.hibernate.ddl-auto = update
spring.jpa.properties.hibernate.dialect = org.hibernate.dialect.PostgreSQLDialect
```

## 📬 API Endpoints

1. **Acoount API**

| Method | Endpoint    | Description            |
| ------ | ----------- | ---------------------- |
| POST   | `/register` | Register a new user    |
| POST   | `/login`    | Login and authenticate |

2. **Message API**

| Method | Endpoint         | Description               |
| ------ | ---------------- | ------------------------- |
| GET    | `/messages`      | Get all messages          |
| GET    | `/messages/{id}` | Get a message by ID       |
| POST   | `/messages`      | Create a new message      |
| PATCH  | `/messages/{id}` | Update message text by ID |
| DELETE | `/messages/{id}` | Delete a message by ID    |

## 🧪 Sample Test Payloads

1. **User Registration**
   <br>
   Open Postman and send a POST request
   <br>
   Make sure it's running at http://localhost:8080/register

   ```bash
       {
           "username": "user",
           "password": "abcd1234"
       }
   ```

2. **User Login**
   <br>
   Send a POST request
   <br>
   Make sure it's running at http://localhost:8080/login (if running locally)

   ```bash
       {
            "username": "user",
            "password": "abcd1234"
       }
   ```

3. **Creation of new messages**
   <br>
   Send a POST request
   <br>
   Make sure it's running at http://localhost:8080/messages (if running locally)

   ```bash
       {
           "messageText": "Hello, this is my first message",
           "accountId": 1
       }

   ```

4. **Update a message text identified by a message ID**
   <br>
   Send a PATCH request
   <br>
   Make sure it's running at http://localhost:8080/messages/1 (if running locally)

   ```bash
           {
                "messageText": "Updated message content"
           }


   ```

## 🧠 Future Enhancements

<ul>
<li> JWT-based Authentication

<li> Like and comment system

<li> Follow/Unfollow users

<li> Media upload support

</ul>
