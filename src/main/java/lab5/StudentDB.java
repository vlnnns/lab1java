package lab5;

import lab1.Student;

import java.sql.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class StudentDB {

    public static void createStudentTable() {
        try (Connection connection = DatabaseConnection.getConnection();
             Statement statement = connection.createStatement()) {
            String createTableQuery = "CREATE TABLE IF NOT EXISTS students (" +
                    "id SERIAL PRIMARY KEY," +
                    "name VARCHAR(50) NOT NULL," +
                    "birth_date DATE" +
                    ")";
            statement.execute(createTableQuery);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public static void dropStudentTable() {
        try (Connection connection = DatabaseConnection.getConnection();
             Statement statement = connection.createStatement()) {

            String dropForeignKeyQuery = "ALTER TABLE IF EXISTS grades DROP CONSTRAINT IF EXISTS grades_student_id_fkey";
            statement.execute(dropForeignKeyQuery);

            String dropTableQuery = "DROP TABLE IF EXISTS students";
            statement.execute(dropTableQuery);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public static void addStudent(Student student) {
        try (Connection connection = DatabaseConnection.getConnection();
             PreparedStatement statement = connection.prepareStatement(
                     "INSERT INTO students (name, birth_date) VALUES (?, ?)"
             )) {
            statement.setString(1, student.getName());
            statement.setObject(2, student.getDateOfBirth());

            statement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }


    public static void updateStudent(Student student) {
        try (Connection connection = DatabaseConnection.getConnection();
             PreparedStatement statement = connection.prepareStatement(
                     "UPDATE students SET name = ?, birth_date = ? WHERE id = ?"
             )) {
            statement.setString(1, student.getName());
            statement.setObject(2, student.getDateOfBirth());
            statement.setInt(3, student.getId());

            statement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }


    public static void deleteStudent(int id) {
        try (Connection connection = DatabaseConnection.getConnection();
             PreparedStatement statement = connection.prepareStatement("DELETE FROM students WHERE id = ?")) {
            statement.setInt(1, id);
            statement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    private List<Student> fromResultSet(ResultSet resultSet) throws SQLException {
        List<Student> matchingStudents = new ArrayList<>();
        while (resultSet.next()) {
            int id = resultSet.getInt("id");
            String studentName = resultSet.getString("name");
            LocalDate birthDate = resultSet.getObject("birth_date", LocalDate.class);

            Student student = new Student.StudentBuilder(studentName).id(id).dateOfBirth(birthDate).build();
            matchingStudents.add(student);

        }
        return matchingStudents;
    }

    public List<Student> getAllStudents() throws SQLException {
        try (Connection connection = DatabaseConnection.getConnection();
             PreparedStatement statement = connection.prepareStatement("SELECT * FROM students")) {
            return fromResultSet(statement.executeQuery());
        }
    }

    public Student getStudentById(int studentId) throws SQLException {
        try (Connection connection = DatabaseConnection.getConnection();
             PreparedStatement statement = connection.prepareStatement("SELECT * FROM students WHERE id = ?")) {
            statement.setInt(1, studentId);
            return fromResultSet(statement.executeQuery()).get(0);
        }
    }

    public List<Student> sortStudentsByName() throws SQLException {
        try (Connection connection = DatabaseConnection.getConnection();
             PreparedStatement statement = connection.prepareStatement("SELECT * FROM students ORDER BY name")) {
            return fromResultSet(statement.executeQuery());
        }
    }

    public List<Student> sortStudentsByDateOfBirth() throws SQLException {
        List<Student> students = new ArrayList<>();
        try (Connection connection = DatabaseConnection.getConnection();
             PreparedStatement statement = connection.prepareStatement("SELECT * FROM students ORDER BY birth_date")) {
            return fromResultSet(statement.executeQuery());
        }
    }

    public List<Student> sortStudentsByAverageGrade() throws SQLException {
        List<Student> students = new ArrayList<>();
        try (Connection connection = DatabaseConnection.getConnection();
             PreparedStatement statement = connection.prepareStatement("SELECT students.id, students.name, students.birth_date, AVG(grades.grade_value) AS average_grade " +
                     "FROM students " +
                     "LEFT JOIN grades ON students.id = grades.student_id " +
                     "GROUP BY students.id, students.name, students.birth_date " +
                     "ORDER BY AVG(grades.grade_value) DESC")) {
            return fromResultSet(statement.executeQuery());
        }
    }


    public List<Student> getByPartName(String name) throws SQLException {
        List<Student> matchingStudents = new ArrayList<>();
        try (Connection connection = DatabaseConnection.getConnection();
             PreparedStatement statement = connection.prepareStatement("SELECT * FROM students WHERE name LIKE ?")) {
            statement.setString(1, "%" + name + "%");
            return fromResultSet(statement.executeQuery());
        }
    }


    public List<Student> getByCurrentMonth() throws SQLException {
        List<Student> studentsInCurrentMonth = new ArrayList<>();
        try (Connection connection = DatabaseConnection.getConnection();
             PreparedStatement statement = connection.prepareStatement(
                     "SELECT * FROM students WHERE EXTRACT(MONTH FROM birth_date) = EXTRACT(MONTH FROM CURRENT_DATE)"
             )) {
            return fromResultSet(statement.executeQuery());
        }
    }
}
