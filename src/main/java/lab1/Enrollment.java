package lab1;

import lab1.DatabaseConnection;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Objects;

public class Enrollment {
    private int id;
    private Subject subject;
    private double grade;

    public Enrollment(Subject subject, double grade, int id) {
        this.id = id;
        this.subject = subject;
        this.grade = grade;
    }

    @Override
    public String toString() {
        return "Enrollment{id ="+ id + ", subject=" + subject + ", grade=" + grade + "}";
    }
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }
    public Subject getSubject() {
        return subject;
    }

    public double getGrade() {
        return grade;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (obj == null || getClass() != obj.getClass()) return false;
        Enrollment enrollment = (Enrollment) obj;
        return Objects.equals(subject, enrollment.subject);
    }

    @Override
    public int hashCode() {
        return Objects.hash(subject);
    }

    public static void createTable() {
        try (Connection connection = DatabaseConnection.getConnection();
             Statement statement = connection.createStatement()) {
            String createTableQuery = "CREATE TABLE IF NOT EXISTS enrollments (" +
                    "id SERIAL PRIMARY KEY," +
                    "subject_id INTEGER," + // Замінено стовпець subject на subject_id
                    "grade FLOAT," +
                    "FOREIGN KEY (subject_id) REFERENCES subjects(id)" + // Виправлено ім'я зовнішнього ключа на subject_id
                    ");";

            statement.execute(createTableQuery);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }




}