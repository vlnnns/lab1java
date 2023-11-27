package lab5;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;

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
}
