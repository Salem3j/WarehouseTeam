import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DatabaseConnection {
    private static final String URL = "jdbc:mysql://localhost:3306/testdb"; // to be replaced with your database URL
    private static final String USER = "root"; //to be replaced with your database username
    private static final String PASSWORD = "password"; //to be replaced with your database password

    public static Connection getConnection() throws SQLException {
        return DriverManager.getConnection(URL, USER, PASSWORD);
    }
}
