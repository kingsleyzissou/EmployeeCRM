# EmployeeCRM

Distributed systems assignment

## NB

My local database forced me to create a password for the database connection,
otherwise connection was to the database was refused.

Please remove the password field in the `core/DBConnection` class as required.

## Structure

This application makes use of a MVC architecture.

Model - all database queries are run by the model
View - used to display the employee information to the user
Controller - renders the views and supplies the data from the model to the views

## Database

The EmployeeCRM makes use of a mysql database. The table has been given a unqiue name to ensure
no conflicts with other students' projects. The SQL dump is enclosed in this project. The table name is prefixed with my student number.
