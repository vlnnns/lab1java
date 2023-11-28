package lab5;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class GradeDB {

    public static void createGradeTable() {
        try (Connection connection = DatabaseConnection.getConnection();
             Statement statement = connection.createStatement()) {
            String createTableQuery = "CREATE TABLE IF NOT EXISTS grades (" +
                    "id SERIAL PRIMARY KEY," +
                    "student_id BIGINT NOT NULL," +
                    "subject_id BIGINT NOT NULL," +
                    "grade_value FLOAT NOT NULL," +
                    "FOREIGN KEY (student_id) REFERENCES students(id) ON DELETE CASCADE," +
                    "FOREIGN KEY (subject_id) REFERENCES subjects(id) ON DELETE CASCADE" +
                    ")";
            statement.execute(createTableQuery);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    public static void dropGradeTable() {
        try (Connection connection = DatabaseConnection.getConnection();
             Statement statement = connection.createStatement()) {
            String dropTableQuery = "DROP TABLE IF EXISTS grades";
            statement.execute(dropTableQuery);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public static void addGrade(long studentId, long subjectId, float gradeValue) {
        try (Connection connection = DatabaseConnection.getConnection();
             PreparedStatement statement = connection.prepareStatement(
                     "INSERT INTO grades (student_id, subject_id, grade_value) VALUES (?, ?, ?)"
             )) {
            statement.setLong(1, studentId);
            statement.setLong(2, subjectId);
            statement.setFloat(3, gradeValue);
            statement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public static List<Float> getGradesByStudentId(long studentId) {
        List<Float> grades = new ArrayList<>();
        try (Connection connection = DatabaseConnection.getConnection();
             PreparedStatement statement = connection.prepareStatement(
                     "SELECT grade_value FROM grades WHERE student_id = ?"
             )) {
            statement.setLong(1, studentId);
            ResultSet resultSet = statement.executeQuery();
            while (resultSet.next()) {
                float grade = resultSet.getFloat("grade_value");
                grades.add(grade);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return grades;
    }

    public static void updateGradeValue(int gradeId, float newGradeValue) {
        try (Connection connection = DatabaseConnection.getConnection();
             PreparedStatement statement = connection.prepareStatement(
                     "UPDATE grades SET grade_value = ? WHERE id = ?"
             )) {
            statement.setFloat(1, newGradeValue);
            statement.setInt(2, gradeId);
            statement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public static void deleteGrade(int gradeId) {
        try (Connection connection = DatabaseConnection.getConnection();
             PreparedStatement statement = connection.prepareStatement(
                     "DELETE FROM grades WHERE id = ?"
             )) {
            statement.setInt(1, gradeId);
            statement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }


}
