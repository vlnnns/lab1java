package lab5;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;
import java.time.LocalDate;

public class StudentDB {

    public static void createStudentTable() {
        try (Connection connection = DatabaseConnection.getConnection();
             Statement statement = connection.createStatement()) {
            String createTableQuery = "CREATE TABLE IF NOT EXISTS students (" +
                    "id SERIAL PRIMARY KEY," +
                    "first_name VARCHAR(50) NOT NULL," +
                    "last_name VARCHAR(50) NOT NULL," +
                    "email VARCHAR(100) NOT NULL," +
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

}
