import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

// Initialize the database connection
public class DatabaseConnection {
    private static Connection connection;

    public static Connection getConnection() {
        if (connection == null) {
            try {
                Class.forName("com.mysql.cj.jdbc.Driver");
                connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/your_database", "username", "password");
            } catch (ClassNotFoundException | SQLException e) {
                e.printStackTrace();
            }
        }
        return connection;
    }
}

// Refactor registration to save user data to the database
private static void registerUser() {
    // ... Input user data

    try (Connection conn = DatabaseConnection.getConnection()) {
        String sql = "INSERT INTO users (username, password, name, age, interests) VALUES (?, ?, ?, ?, ?)";
        PreparedStatement statement = conn.prepareStatement(sql);
        statement.setString(1, username);
        statement.setString(2, password);
        statement.setString(3, name);
        statement.setInt(4, age);
        statement.setString(5, interests);
        statement.executeUpdate();
        System.out.println("Registration successful!");
    } catch (SQLException e) {
        e.printStackTrace();
        System.out.println("Registration failed.");
    }
}
