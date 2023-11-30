package lab5;

import lab1.Student;

import java.sql.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Comparator;
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

    public static void addStudent(String name, LocalDate birthDate) {
        try (Connection connection = DatabaseConnection.getConnection();
             PreparedStatement statement = connection.prepareStatement(
                     "INSERT INTO students (name, birth_date) VALUES (?, ?)"
             )) {
            statement.setString(1, name);
            statement.setObject(2, birthDate);

            statement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }


    public static List<Student> getAllStudents() {
        List<Student> students = new ArrayList<>();
        try (Connection connection = DatabaseConnection.getConnection();
             Statement statement = connection.createStatement()) {
            ResultSet resultSet = statement.executeQuery("SELECT id, name, birth_date FROM students");
            while (resultSet.next()) {
                int id = resultSet.getInt("id");
                String name = resultSet.getString("name");
                Date sqlBirthDate = resultSet.getDate("birth_date");

                LocalDate birthDate = null;
                if (sqlBirthDate != null) {
                    birthDate = sqlBirthDate.toLocalDate();
                } else {
                    System.out.println("SQL Birth Date is null for ID: " + id);
                }

                // Check the retrieved values
                System.out.println("ID: " + id + ", Name: " + name + ", Birth Date: " + birthDate);

                Student student = new Student(id, name, birthDate);
                students.add(student);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return students;
    }

    public static Student getStudentById(int studentId) {
        Student student = null;
        try (Connection connection = DatabaseConnection.getConnection();
             PreparedStatement statement = connection.prepareStatement("SELECT id, name, birth_date FROM students WHERE id = ?")) {

            statement.setInt(1, studentId);
            ResultSet resultSet = statement.executeQuery();

            if (resultSet.next()) {
                int id = resultSet.getInt("id");
                String name = resultSet.getString("name");
                LocalDate birthDate = resultSet.getDate("birth_date").toLocalDate(); // Retrieve and convert date

                student = new Student(id, name, birthDate);

                // Printing retrieved values
                System.out.println("Retrieved ID: " + id + ", Name: " + name + ", Birth Date: " + birthDate);

            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return student;
    }



    public static void updateStudent(int id, String name, LocalDate birthDate) {
        try (Connection connection = DatabaseConnection.getConnection();
             PreparedStatement statement = connection.prepareStatement(
                     "UPDATE students SET name = ?, birth_date = ? WHERE id = ?"
             )) {
            statement.setString(1, name);
            statement.setObject(2, birthDate);
            statement.setInt(3, id);

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

    public static List<Student> sortStudentsByName() {
        List<Student> students = new ArrayList<>();
        try (Connection connection = DatabaseConnection.getConnection();
             PreparedStatement statement = connection.prepareStatement(
                     "SELECT * FROM students ORDER BY name"
             );
             ResultSet resultSet = statement.executeQuery()) {

            while (resultSet.next()) {
                int id = resultSet.getInt("id");
                String name = resultSet.getString("name");
                LocalDate birthDate = resultSet.getObject("birth_date", LocalDate.class);

                Student student = new Student(id, name, birthDate);
                students.add(student);

                System.out.println("ID: " + id + ", Name: " + name + ", Birth Date: " + birthDate);

            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return students;
    }

    public static List<Student> sortStudentsByDateOfBirth() {
        List<Student> students = new ArrayList<>();
        try (Connection connection = DatabaseConnection.getConnection();
             PreparedStatement statement = connection.prepareStatement(
                     "SELECT * FROM students ORDER BY birth_date"
             );
             ResultSet resultSet = statement.executeQuery()) {

            while (resultSet.next()) {
                int id = resultSet.getInt("id");
                String name = resultSet.getString("name");
                LocalDate birthDate = resultSet.getObject("birth_date", LocalDate.class);

                Student student = new Student(id, name, birthDate);
                students.add(student);

                System.out.println("ID: " + id + ", Name: " + name + ", Birth Date: " + birthDate);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return students;
    }

    public static List<Student> sortStudentsByAverageGrade() {
        List<Student> students = new ArrayList<>();
        try (Connection connection = DatabaseConnection.getConnection();
             PreparedStatement statement = connection.prepareStatement(
                     "SELECT students.id, students.name, students.birth_date, AVG(grades.grade_value) AS average_grade " +
                             "FROM students " +
                             "LEFT JOIN grades ON students.id = grades.student_id " +
                             "GROUP BY students.id, students.name, students.birth_date " +
                             "ORDER BY AVG(grades.grade_value) DESC"
             );
             ResultSet resultSet = statement.executeQuery()) {

            while (resultSet.next()) {
                int id = resultSet.getInt("id");
                String name = resultSet.getString("name");
                LocalDate birthDate = resultSet.getObject("birth_date", LocalDate.class);

                Student student = new Student(id, name, birthDate);
                students.add(student);
                System.out.println("ID: " + id + ", Name: " + name + ", Birth Date: " + birthDate);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return students;
    }

    public static List<Student> getByPartName(String name) {
        List<Student> matchingStudents = new ArrayList<>();
        try (Connection connection = DatabaseConnection.getConnection();
             PreparedStatement statement = connection.prepareStatement("SELECT * FROM students WHERE name LIKE ?")) {
            statement.setString(1, "%" + name + "%");
            ResultSet resultSet = statement.executeQuery();

            while (resultSet.next()) {
                int id = resultSet.getInt("id");
                String studentName = resultSet.getString("name");
                LocalDate birthDate = resultSet.getObject("birth_date", LocalDate.class);

                Student student = new Student(id, studentName, birthDate);
                matchingStudents.add(student);

                System.out.println("ID: " + id + ", Name: " + studentName + ", Birth Date: " + birthDate);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        matchingStudents.sort(Comparator.comparing(Student::getName, Comparator.nullsLast(Comparator.naturalOrder())));

        return matchingStudents;
    }


    public static List<Student> getByCurrentMonth() {
        List<Student> studentsInCurrentMonth = new ArrayList<>();
        try (Connection connection = DatabaseConnection.getConnection();
             PreparedStatement statement = connection.prepareStatement(
                     "SELECT * FROM students WHERE EXTRACT(MONTH FROM birth_date) = EXTRACT(MONTH FROM CURRENT_DATE)"
             )) {
            ResultSet resultSet = statement.executeQuery();
            while (resultSet.next()) {
                int id = resultSet.getInt("id");
                String name = resultSet.getString("name");
                LocalDate birthDate = resultSet.getObject("birth_date", LocalDate.class);

                Student student = new Student(id, name, birthDate);
                studentsInCurrentMonth.add(student);
                System.out.println("ID: " + id + ", Name: " + name + ", Birth Date: " + birthDate);
            }
            studentsInCurrentMonth.sort(Comparator.comparing(Student::getName));
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return studentsInCurrentMonth;
    }

}
