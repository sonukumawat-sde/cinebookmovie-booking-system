# 🎬 CineBook - Next-Gen Movie Ticket Booking System

![Java](https://img.shields.io/badge/Java-ED8B00?style=for-the-badge&logo=java&logoColor=white)
![Spring Boot](https://img.shields.io/badge/Spring_Boot-F2F4F9?style=for-the-badge&logo=spring-boot)
![JavaScript](https://img.shields.io/badge/JavaScript-323330?style=for-the-badge&logo=javascript&logoColor=F7DF1E)
![HTML5](https://img.shields.io/badge/HTML5-E34F26?style=for-the-badge&logo=html5&logoColor=white)
![CSS3](https://img.shields.io/badge/CSS3-1572B6?style=for-the-badge&logo=css3&logoColor=white)

## 🚀 Overview
**CineBook** is a modern, full-stack Movie Ticket Booking System designed to replicate a premium cinema booking experience (similar to BookMyShow). Built with a robust **Spring Boot** backend and a sleek, responsive **Vanilla JavaScript/HTML/CSS** frontend, it features a minimalist dark-mode UI, real-time seat mapping, and secure user authentication.

## ✨ Key Features
* **User Authentication:** Secure Sign-Up and Login functionality with role-based access control.
* **Premium Dark UI:** A highly responsive, cinematic "Glassmorphism" interface built with CSS Grid and Flexbox.
* **Interactive Seat Mapping:** Real-time dynamic seat selection layout with algorithm-based randomized seat occupancy for a realistic feel.
* **Dynamic Checkout:** Real-time total price calculation based on the number of selected seats.
* **Simulated Payment Gateway:** Processing animation leading to a verified digital ticket generation with a unique PNR/Order ID.
* **Bento-Grid Movie Selection:** A sleek movie selection interface that dynamically updates the hero section and booking context.

## 🛠️ Tech Stack
* **Frontend:** HTML5, CSS3 (Custom Variables, Flexbox, Grid), Vanilla JavaScript (ES6+, Fetch API).
* **Backend:** Java 17, Spring Boot, Spring Data JPA, Hibernate.
* **Database:** MySQL / H2 Database (Integrated via Spring Data).
* **Architecture:** MVC (Model-View-Controller) & RESTful APIs.

## 📂 Project Structure
This repository contains the complete full-stack project, organized into distinct modules:
```text
📦 CineBook-Movie-Booking-System
 ┣ 📂 backend                 # Primary Spring Boot Application (REST APIs)
 ┣ 📂 frontend                # Main UI files (index.html, style.css, script.js)
 ┗ 📂 backend-old-corejava    # Legacy Core Java version (Archived for reference)
⚙️ How to Run Locally
1. Start the Backend (Spring Boot)
Navigate to the backend directory.

Ensure you have Java 17+ and Maven installed.

Update your database credentials in application.properties (if using MySQL).

Run the application using Maven:

Bash
./mvnw spring-boot:run
The backend server will start at http://localhost:8080.

2. Start the Frontend
Navigate to the frontend directory.

Simply open index.html in your favorite web browser (Chrome, Edge, Firefox).

The frontend is configured to communicate with the local backend running on port 8080.

📸 Screenshots
(Add screenshots of your project here by dragging and dropping images into this README file on GitHub)

Home & Movie Selection - [Screenshot Placeholder]

Interactive Seat Layout - [Screenshot Placeholder]

Confirmed E-Ticket - [Screenshot Placeholder]

👨‍💻 Developed by: Sonu Kumawat | 2nd Year Engineering Student
