package org.example;

import java.sql.*;

public class Main {
    public static String url = "jdbc:postgresql://localhost:5432/Assignment3-1";
    public static String user = "postgres";
    public static String password = "jorg1357";

    public static void main(String[] args) {
//        Class.forName("org.postgresql.Driver");
        System.out.println("RETRIEVING ALL STUDENTS");
        getAllStudents();

        System.out.println("\nADDING NEW STUDENT");
        addStudent("James", "Pearpicker", "james.pearpicker@example.com", new Date(System.currentTimeMillis()));

        System.out.println("RETRIEVING ALL STUDENTS");
        getAllStudents();

        System.out.println("\nUPDATING STUDENT EMAIL");
        updateStudentEmail(1, "john.doe2@example.com");

        System.out.println("RETRIEVING ALL STUDENTS");
        getAllStudents();

        System.out.println("\nDELETING STUDENT");
        deleteStudent(1);

        System.out.println("RETRIEVING ALL STUDENTS");
        getAllStudents();
    }

    public static void getAllStudents(){
        try{

            Connection connection = DriverManager.getConnection(url,user,password);
            Statement statement = connection.createStatement();
            statement.executeQuery("SELECT * FROM students");
            ResultSet resultSet = statement.getResultSet();
            while (resultSet.next()){
                System.out.println(resultSet.getInt("student_id") + ", "
                        + resultSet.getString("first_name") + " "
                        + resultSet.getString("last_name") + ", "
                        + resultSet.getString("email") + ", "
                        + resultSet.getDate("enrollment_date"));
            }
            resultSet.close();
            statement.close();
            connection.close();
        }catch (SQLException e){
            e.printStackTrace();
        }
    }

    public static void addStudent(String firstName, String lastName, String email, Date enrollmentDate){
        try{

            Connection connection = DriverManager.getConnection(url,user,password);
            PreparedStatement preparedStatement = connection.prepareStatement("INSERT INTO students (first_name, last_name, email, enrollment_date) VALUES (?, ?, ?, ?)");
            preparedStatement.setString(1, firstName);
            preparedStatement.setString(2, lastName);
            preparedStatement.setString(3, email);
            preparedStatement.setDate(4, enrollmentDate);
            preparedStatement.executeUpdate();
            System.out.println("Student added successfully.");
            preparedStatement.close();
            connection.close();
        }catch (SQLException e){
            e.printStackTrace();
        }
    }

    public static void updateStudentEmail(int studentId, String newEmail){
        try{
            Connection connection = DriverManager.getConnection(url,user,password);
            PreparedStatement preparedStatement = connection.prepareStatement("UPDATE students SET email = ? WHERE student_id = ?");
            preparedStatement.setString(1, newEmail);
            preparedStatement.setInt(2, studentId);
            preparedStatement.executeUpdate();
            System.out.println("Student email updated successfully.");
            preparedStatement.close();
            connection.close();
        }catch (SQLException e){
            e.printStackTrace();
        }
    }

    public static void deleteStudent(int studentId){
        try{

            Connection connection = DriverManager.getConnection(url,user,password);
            PreparedStatement preparedStatement = connection.prepareStatement("DELETE FROM students WHERE student_id = ?");
            preparedStatement.setInt(1, studentId);
            preparedStatement.executeUpdate();
            System.out.println("Student deleted successfully.");
            preparedStatement.close();
            connection.close();
        }catch (SQLException e){
            e.printStackTrace();
        }
    }



}