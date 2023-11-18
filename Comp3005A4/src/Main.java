import java.sql.*;
import java.util.Scanner;

public class Main {

    //Databse URL, When executing this code switch the host, port, and db name to whatever it is locally
    static String URL = "jdbc:postgresql://localhost:5432/A4Students";

    //UName and Password, I will have these blank when I submit this assignment, marking TA will have to put their
    // local values in here
    static String username = "";
    static String pwd = "";

    public static void main(String[] args) throws SQLException {

        int input; //for user input
        Connection connection = DriverManager.getConnection(URL, username, pwd); //init DB connection

        //Command line input loop
        while(true) {
            System.out.println("\n1. Get all students");
            System.out.println("2. Add a student");
            System.out.println("3. Update a student's email");
            System.out.println("4. Delete a student");
            System.out.println("5. Quit");

            Scanner scanner = new Scanner(System.in);
            System.out.print("Input: ");
            input = scanner.nextInt();

            switch(input) {
                case 1:
                    getAllStudents(connection);
                    break;
                case 2:
                    addStudent(connection);
                    break;
                case 3:
                    updateStudentEmail(connection);
                    break;
                case 4:
                    deleteStudent(connection);
                    break;
                case 5:
                    connection.close();
                    return;
                default:
                    System.out.println("Invalid choice. ");
            }
        }
    }

    //Function that will return all students formatted into a table.
    static void getAllStudents(Connection connection) throws SQLException {

        String query = "SELECT * FROM students";

        //Sends the query, and gets the results back
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(query);
            ResultSet resultSet = preparedStatement.executeQuery();

            //loops through the results and outputs them nicely
            System.out.printf("| %-2s | %-20s | %-20s | %-30s | %-20s |\n", "ID", "First Name", "Last Name", "Email", "Enrollment Date");
            while(resultSet.next()) {
                System.out.printf("| %-2s | %-20s | %-20s | %-30s | %-20s |\n",
                        resultSet.getInt("student_id"),
                        resultSet.getString("first_name"),
                        resultSet.getString("last_name"),
                        resultSet.getString("email"),
                        resultSet.getDate("enrollment_date"));
            }
        } catch(SQLException e) {
            System.out.println("Error: " + e.getMessage());
        }
    }


    //Function that will add a new student to the database.
    static void addStudent(Connection connection) throws SQLException {
        //variable declarations, notably no StudentID since ID is auto incrementing
        Scanner scanner = new Scanner(System.in);
        String firstName;
        String lastName;
        String email;
        Date enrollmentDate;
        String query = "INSERT INTO students (first_name, last_name, email, enrollment_date) VALUES (?, ?, ?, ?)";

        //User input
        System.out.print("First Name: ");
        firstName = scanner.nextLine();
        System.out.print("Last Name: ");
        lastName = scanner.nextLine();
        System.out.print("Email: ");
        email = scanner.nextLine();
        System.out.print("Enrollment Date (YYYY-MM-DD): ");
        enrollmentDate = Date.valueOf(scanner.nextLine()); //converts the string input to the proper date format

        //prepares query, updates query with user inputted values, executes query.
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(query);
            preparedStatement.setString(1, firstName);
            preparedStatement.setString(2, lastName);
            preparedStatement.setString(3, email);
            preparedStatement.setDate(4, enrollmentDate);
            preparedStatement.executeUpdate();

            System.out.println("Student added.");
        } catch(SQLException e) {
            System.out.println("Error: " + e.getMessage());
        }
    }


    //Function that will update a selected students email to a new email.
    static void updateStudentEmail(Connection connection) throws SQLException {
        Scanner scanner = new Scanner(System.in);
        int student;
        String email;
        String query = "UPDATE students SET email = ? WHERE student_id = ?";

        //User input
        System.out.print("Enter the desired studentID: ");
        student = Integer.parseInt(scanner.nextLine());
        System.out.print("Enter the new email address: ");
        email = scanner.nextLine();

        //prepares the query, updates the query with desired values, then executes.
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(query);
            preparedStatement.setString(1, email);
            preparedStatement.setInt(2, student);
            preparedStatement.executeUpdate();

            System.out.println("Update successful.");

        } catch(SQLException e) {
            System.out.println("Error: " + e.getMessage());
        }
    }

    //Function to delete a student from the table.
    static void deleteStudent(Connection connection) throws SQLException {
        Scanner scanner = new Scanner(System.in);
        int student;
        String query = "DELETE FROM students WHERE student_id = ?";

        //User input
        System.out.print("Enter the ID of the student to delete: ");
        student = Integer.parseInt(scanner.nextLine());

        //prepares query, updates query with user input, executes query.
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(query);
            preparedStatement.setInt(1, student);
            preparedStatement.executeUpdate();

            System.out.println("Student deleted.");

        } catch(SQLException e) {
            System.out.println("Error: " + e.getMessage());
        }
    }
}