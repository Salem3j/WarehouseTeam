import java.sql.Connection;
import java.sql.Statement;
import java.sql.SQLException;

public class UserTableManager {

    // Method to create the users table
    public static void createUsersTable() {
        String sql = "CREATE TABLE IF NOT EXISTS users (" +
                "id INT AUTO_INCREMENT PRIMARY KEY, " +
                "username VARCHAR(50), " +
                "email VARCHAR(50))";
        try (Connection conn = DatabaseConnection.getConnection();
             Statement stmt = conn.createStatement()) {
            stmt.executeUpdate(sql);
            System.out.println("Users table created successfully.");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    // Method to add a new column
    public static void addColumnToUsersTable(String columnName, String columnType) {
        String sql = "ALTER TABLE users ADD COLUMN " + columnName + " " + columnType;
        try (Connection conn = DatabaseConnection.getConnection();
             Statement stmt = conn.createStatement()) {
            stmt.executeUpdate(sql);
            System.out.println("Column " + columnName + " added successfully.");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    // Method to add a user to the table
    public static void addUser(String username, String email) {
        String sql = "INSERT INTO users (username, email) VALUES (?, ?)";
        try (Connection conn = DatabaseConnection.getConnection();
             var pstmt = conn.prepareStatement(sql)) {
            pstmt.setString(1, username);
            pstmt.setString(2, email);
            pstmt.executeUpdate();
            System.out.println("User added successfully.");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
