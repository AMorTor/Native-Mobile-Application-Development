# Practice 2 - Creatting a CRUD with SpringBoot
# Practice2 Backend

This project is a backend API created with Java Spring Boot for the Native Mobile Application Development course.

## Table of Contents
- [Introduction](#introduction)
- [Features](#features)
- [Installation](#installation)
- [Usage](#usage)
- [API Endpoints](#api-endpoints)
- [Contributing](#contributing)
- [License](#license)

## Introduction
The Practice2 Backend is designed to provide RESTful API services for a native mobile application. It handles data management, authentication, and other backend functionalities.

## Features
- RESTful API endpoints
- CRUD operations for various entities
- Integration with a database

## Installation
1. Clone the repository:
    ```bash
    git clone https://github.com/AMorTor/Native-Mobile-Application-Development.git
    ```
2. Navigate to the project directory:
    ```bash
    cd Practice2-backend
    ```
3. Build the project using Maven:
    ```bash
    mvn clean install
    ```
4. Run the application:
    ```bash
    mvn spring-boot:run
    ```

## Usage
Once the application is running, you can access the API at `http://localhost:8080`.

## API Endpoints
- `GET /api/users` - Retrieve a list of users
- `POST /api/users` - Create a new user
- `GET /api/users/{id}` - Retrieve a user by ID
- `PUT /api/users/{id}` - Update a user by ID
- `DELETE /api/users/{id}` - Delete a user by ID

## Contributing
Contributions are welcome! Please fork the repository and create a pull request with your changes.

## License
This project is licensed under the MIT License.
