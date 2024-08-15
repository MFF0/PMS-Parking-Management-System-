Parking Management System
Spring Boot was used to create this backend-only parking management system. Users can reserve parking spaces in existing garages and manage their vehicles with it. Since this is my first project utilizing Spring Boot, it acts as a practice and learning exercise.

Features
User Management:
Create a new user account.
View and manage user details.

Vehicle Management:
Add vehicles associated with a user account.
View and manage vehicles.

Parking Slot Reservation:
Reserve parking slots in existing garages.
View and manage reservations.

Technologies Used
Spring Boot: A framework that simplifies the development of Java applications.
JPA/Hibernate: For database management and interaction.
MySQL: The relational database management system used for storing user, vehicle, and reservation data.

Prerequisites:

Java 17 or higher
Maven
MySQL
Setup

Clone the repository:

git clone https://github.com/MFF0/PMS.git
cd PMS

To configure the database:

Create a new MySQL database.
Update the application.properties file with your database configuration:

spring.application.name=PMS
spring.datasource.url=jdbc:mysql://localhost:3306/ParkingManagementSys
spring.datasource.username=your username
spring.datasource.password= your password
spring.jpa.hibernate.ddl-auto=update
spring.jpa.show-sql=true
spring.jpa.hibernate.dialect=org.hibernate.dialect.MySQLDialect
spring.jpa.hibernate.format_sql=true

To run the application:

mvn spring-boot:run

Usage:
Use tools like Postman to interact with the API endpoints.
Perform operations such as creating users, adding vehicles, and reserving parking slots.

Learning Experience
This project is my first experience with Spring Boot. It has helped me understand how to build and structure a backend application, manage data with JPA/Hibernate, and implement REST APIs.
