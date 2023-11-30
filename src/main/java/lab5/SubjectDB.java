package lab5;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class SubjectDB {

    public static void createSubjectTable() {
        try (Connection connection = DatabaseConnection.getConnection();
             Statement statement = connection.createStatement()) {
            String createTableQuery = "CREATE TABLE IF NOT EXISTS subjects (" +
                    "id SERIAL PRIMARY KEY," +
                    "subject_name VARCHAR(100) NOT NULL" +
                    ")";
            statement.execute(createTableQuery);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public static void dropSubjectTable() {
        try (Connection connection = DatabaseConnection.getConnection();
             Statement statement = connection.createStatement()) {
            String dropTableQuery = "DROP TABLE IF EXISTS subjects CASCADE";
            statement.execute(dropTableQuery);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public static void addSubject(String subjectName) {
        try (Connection connection = DatabaseConnection.getConnection();
             PreparedStatement statement = connection.prepareStatement(
                     "INSERT INTO subjects (subject_name) VALUES (?)"
             )) {
            statement.setString(1, subjectName);
            statement.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public static List<String> getAllSubjects() {
        List<String> subjects = new ArrayList<>();
        try (Connection connection = DatabaseConnection.getConnection();
             Statement statement = connection.createStatement()) {
            ResultSet resultSet = statement.executeQuery("SELECT subject_name FROM subjects");
            while (resultSet.next()) {
                String subjectName = resultSet.getString("subject_name");
                subjects.add(subjectName);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return subjects;
    }

    public static String getSubjectById(int id) {
        String subjectName = null;
        try (Connection connection = DatabaseConnection.getConnection();
             PreparedStatement statement = connection.prepareStatement(
                     "SELECT subject_name FROM subjects WHERE id = ?"
             )) {
            statement.setInt(1, id);
            ResultSet resultSet = statement.executeQuery();

            if (resultSet.next()) {
                subjectName = resultSet.getString("subject_name");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return subjectName;
    }

    public static void updateSubjectName(int id, String newSubjectName) {
        try (Connection connection = DatabaseConnection.getConnection();
             PreparedStatement statement = connection.prepareStatement(
                     "UPDATE subjects SET subject_name = ? WHERE id = ?"
             )) {
            statement.setString(1, newSubjectName);
            statement.setInt(2, id);
            statement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public static void deleteSubject(int id) {
        try (Connection connection = DatabaseConnection.getConnection();
             PreparedStatement statement = connection.prepareStatement(
                     "DELETE FROM subjects WHERE id = ?"
             )) {
            statement.setInt(1, id);
            statement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
