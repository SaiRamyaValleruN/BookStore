package bookstoreRound2.databaseConnector;


import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import bookstoreRound2.bookDetails.BookDetails;

public class BookDatabaseConnector {
    private static final String DB_URL = "jdbc:mysql://localhost:3306/bookstore";
    private static final String DB_USER = "root";
    private static final String DB_PASSWORD = "password";

    public static Connection getConnection() throws SQLException {
        return DriverManager.getConnection(DB_URL, DB_USER, DB_PASSWORD);
    }

    public static BookDetails searchBook(String searchTerm) {
        String query = "SELECT * FROM BookDetails " +
                       "WHERE title LIKE ? OR author LIKE ? OR isbn LIKE ?";
        try (Connection connection = getConnection();
             PreparedStatement statement = connection.prepareStatement(query)) {
            statement.setString(1, "%" + searchTerm + "%");
            statement.setString(2, "%" + searchTerm + "%");
            statement.setString(3, "%" + searchTerm + "%");
            try (ResultSet resultSet = statement.executeQuery()) {
                if (resultSet.next()) {
                    return new BookDetails(
                            resultSet.getString("title"),
                            resultSet.getString("author"),
                            resultSet.getString("isbn"),
                            resultSet.getString("publisher"),
                            resultSet.getInt("publicationYear"),
                            resultSet.getInt("edition"),
                            resultSet.getDouble("price"),
                            resultSet.getBoolean("availability")
                    );
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }
}

