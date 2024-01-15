
ChatGPT
Service Documentation

Welcome to the documentation for the UserService in our project!

UserService
Method: getAllUsers

This service method retrieves users from multiple relational databases with optional filters.

Parameters:

nameFilter (optional): Filter users by name.
usernameFilter (optional): Filter users by username.
surnameFilter (optional): Filter users by surname.
Return:

A list of users matching the specified criteria.

Getting Started
To get started, follow these steps:

Run Docker Compose:
Execute docker-compose up to start the PostgreSQL containers for the relational databases.
Run the Application:
Ensure that the Docker containers are running.
Start your Spring Boot application, which interacts with the PostgreSQL databases.
Access the Service:
The UserService is exposed through the /users endpoint.
Additional Information:
Migration:
Database migration is automatically handled for relational databases upon application startup.
Migration scripts are located in the db/migration directory, organized by database.
Multiple Databases:
This service interacts with multiple databases configured in the docker-compose.yml file.
Each database has its dedicated container (e.g., postgres-db1, postgres-db2).
Feel free to explore and use the UserService to retrieve user data based on your filtering criteria. If you encounter any issues or have further questions, consult the documentation or reach out to our support team.
