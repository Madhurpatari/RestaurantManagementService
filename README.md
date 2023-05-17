# Restaurant Management System

The Restaurant Management System is a web application developed using Spring Boot. It allows users to manage food items, place orders, and perform various administrative tasks.

## Features

- User Management: Allows users to sign up, sign in, and manage their profiles.
- Role-based Access Control: Differentiates between admin users, normal users, and visitors, providing appropriate access and privileges.
- Food Item Management: Admin users can create new food items with titles, descriptions, prices, and dummy image URLs.
- Order Placement: Normal users can place orders by selecting food items, specifying quantities, and tracking order statuses.
- Data Persistence: Utilizes a relational database to store user information, food items, and orders.
- RESTful API: Provides a set of endpoints for interacting with users, food items, and orders.
- Security: Implements authentication and authorization to protect sensitive operations and ensure data privacy.

## Technologies Used

- Java
- Spring Boot
- Spring Data JPA
- Hibernate
- MySQL (or any other database of your choice)
- RESTful API

## Getting Started

To run the Restaurant Management System on your local machine, follow these steps:

1. Clone the repository: `git clone https://github.com/your-username/restaurant-management-system.git`
2. Navigate to the project directory: `cd restaurant-management-system`
3. Configure the database connection in the `application.properties` file.
4. Build the project: `./mvnw clean package`
5. Run the application: `./mvnw spring-boot:run`
6. Access the application in your browser: `http://localhost:8080`

## API Endpoints

The following API endpoints are available:

- `/users`: CRUD operations for managing users.
- `/fooditems`: CRUD operations for managing food items (accessible only to admin users).
- `/orders`: CRUD operations for managing orders (accessible only to normal users).

Please refer to the source code or [API documentation](https://documenter.getpostman.com/view/26741522/2s93kz54n4) for detailed information about each endpoint.

## Contributing

Contributions are welcome! If you have any suggestions, improvements, or bug fixes, please open an issue or submit a pull request.
