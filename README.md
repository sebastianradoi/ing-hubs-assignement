# Store Management Tool API

### Project Overview
This project is a simple backend-focused Store Management Tool API developed using Spring Boot. 
It's designed to manage product inventories for a store, allowing users to add products, find products, and update product prices. This Java 17, Maven-based project leverages Spring Boot for the web components, providing a RESTful API.

### Features
**Product Management**: Add new products, find existing products, and change product prices.    
**Basic Authentication**: A simple username and password authentication mechanism to secure endpoints.  
**Role-Based Access Control**: Restrict access to certain endpoints based on user roles.  
**Error Handling**: A global exception handler to manage exceptions and provide meaningful error messages.  
**Logging**: Global logging for tracking operations.  
**Unit Testing**: Includes unit tests for the product management functionality.

### Technology Stack
**Java 17**: The programming language used to develop the application.
**Spring Boot**: Framework for building web applications.  
**Maven**: Manages dependencies.  
**JUnit**: Supports unit testing of the application components.  
**H2 Database**: An in-memory database for persisting products data.

## Getting Started

### Prerequisites
JDK 17  
Maven  
Git  
### Setup and Installation
Clone the Repository  
```git clone https://github.com/sebastianradoi/ing-hubs-assignement.git```   
Build the Project  
```mvn clean install```  
Run the Application  
```mvn spring-boot:run```  
The application will start and be accessible at http://localhost:8080/store-management-service  
The swagger will be available at http://localhost:8080/store-management-service/swagger  