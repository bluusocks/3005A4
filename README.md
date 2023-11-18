# 3005A4
Database Connection Practice Assignment for COMP 3005 Fall Semester 2023.

Contents:
    
  A4StudentsSetup.sql: SQL script to create and populate the students table.
    
  Comp3005A4: Java project folder to run the interface. src -> Main.java is the actual code, lib -> postgresql-42.6.0.jar is a jar file required to run the code.


Database Setup:

  Executing the SQL script onto your database should create the students table and populate it.

To run code:

  Ensure the postgres database is running.
  
  Load the project folder into your IDE of choice.
  
  Edit Main.java and edit the URL, username, and password for the databse connection.
  
  Add the jar file to the build path of the project. (in intelliJ, file -> project structure -> libraries -> click the +, add the jar file).
  
  Execute the code. It should work.


Code description:

  main(): initializes connection, provides a menu to choose which query to execute.

  getAllStudents(): returns all students from the table

  addStudent(): adds a student to the table based on user input

  updateStudentEmail(): updates a specified student's email based on user input

  deleteStudent(): deletes specified student from the table.
