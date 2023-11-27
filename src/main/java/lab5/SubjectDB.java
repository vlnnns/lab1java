package lab5;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;

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
}
