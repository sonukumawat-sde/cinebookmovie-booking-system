<div align="center">
  <h1>🎬 CineBook</h1>
  <h3>Next-Gen Movie Ticket Booking System</h3>
  
  <p align="center">
    <img src="https://img.shields.io/badge/Java-ED8B00?style=for-the-badge&logo=java&logoColor=white" alt="Java" />
    <img src="https://img.shields.io/badge/Spring_Boot-F2F4F9?style=for-the-badge&logo=spring-boot" alt="Spring Boot" />
    <img src="https://img.shields.io/badge/JavaScript-323330?style=for-the-badge&logo=javascript&logoColor=F7DF1E" alt="JavaScript" />
    <img src="https://img.shields.io/badge/MySQL-4479A1?style=for-the-badge&logo=mysql&logoColor=white" alt="MySQL" />
  </p>

  <p align="center">
    <a href="#"><b>[🔗 Live Demo]</b></a> • 
    <a href="#"><b>[📹 Watch Video Tour]</b></a>
  </p>
</div>

---

## 🚀 Overview
**CineBook** is a modern, full-stack Movie Ticket Booking System designed to replicate a premium cinema booking experience (similar to BookMyShow). Built with a robust **Spring Boot** backend and a sleek, responsive **Vanilla JavaScript/HTML/CSS** frontend, it features a minimalist dark-mode UI, real-time seat mapping, and secure user authentication.

## ⚙️ System Architecture (MVC)
The application strictly follows the Model-View-Controller (MVC) architectural pattern:
* **Client Layer (View):** Interactive UI built with Vanilla JS, managing state dynamically using the Fetch API.
* **Controller Layer:** Spring Boot REST APIs handling HTTP requests, routing, and input validation.
* **Service Layer:** Contains core business logic (seat allocation algorithms, price calculations).
* **Data Access Layer (Model):** Spring Data JPA / Hibernate interacting with the MySQL database to manage Movies, Users, and Tickets.

---

## ✨ Key Features
* 🔐 **User Authentication:** Secure Sign-Up and Login functionality with role-based access control.
* 🎨 **Premium Dark UI:** A highly responsive, cinematic "Glassmorphism" interface built with CSS Grid and Flexbox.
* 💺 **Interactive Seat Mapping:** Real-time dynamic seat selection layout with algorithm-based randomized seat occupancy for a realistic feel.
* 💳 **Dynamic Checkout & Payment:** Real-time total price calculation with a simulated payment processing animation.
* 🎟️ **E-Ticket Generation:** Verified digital ticket generation with a unique PNR/Order ID upon successful booking.

---

## 📸 Project Showcase

| Home & Movie Selection | Interactive Seat Layout |
| :---: | :---: |
| *(Drag and drop your home screen image here)* | *(Drag and drop your seat layout image here)* |
| **Checkout Process** | **Confirmed E-Ticket** |
| *(Drag and drop checkout image here)* | *(Drag and drop ticket image here)* |

---

## 📂 Project Structure
This repository contains the complete full-stack project, organized cleanly:

```text
📦 CineBook-Movie-Booking-System
 ┣ 📂 backend                 # Primary Spring Boot Application (REST APIs)
 ┃ ┣ 📂 src/main/java         # Controllers, Services, Models, Repositories
 ┃ ┗ 📜 application.properties# Database & server configurations
 ┣ 📂 frontend                # Main UI files
 ┃ ┣ 📜 index.html            # Main markup
 ┃ ┣ 📜 style.css             # Glassmorphism & layout styles
 ┃ ┗ 📜 script.js             # DOM manipulation & API fetching
 ┗ 📂 backend-old-corejava    # Legacy Core Java version (Archived for reference)
