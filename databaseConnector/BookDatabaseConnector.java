package bookstore.databaseConnector;


import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.sql.Connection;

import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HexFormat;
import java.util.List;

import bookstore.bookDetails.BookDetails;

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
                            resultSet.getBoolean("availability"),
                            resultSet.getString("imagePath"),
                            resultSet.getString("description")
                    );
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }


    public static void addBook(BookDetails book) throws SQLException {
        String sql = "INSERT INTO BookDetails (title, author, isbn, publisher, publicationYear, edition, price, availability, imagePath, description) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
        try (Connection conn = getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setString(1, book.getTitle());
            pstmt.setString(2, book.getAuthor());
            pstmt.setString(3, book.getIsbn());
            pstmt.setString(4, book.getPublisher());
            pstmt.setInt(5, book.getPublicationYear());
            pstmt.setInt(6, book.getEdition());
            pstmt.setDouble(7, book.getPrice());
            pstmt.setBoolean(8, book.isAvailability());
            pstmt.setString(9, book.getImagePath());
            pstmt.setString(10, book.getDescription());
            pstmt.executeUpdate();
        } catch (SQLException e) {
            throw new SQLException("Failed to add book: " + e.getMessage(), e);
        }
    }


    public static BookDetails fetchBookByISBN(String isbn) throws SQLException {
        String sql = "SELECT * FROM BookDetails WHERE isbn = ?";
        try (Connection conn = getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setString(1, isbn);
            try (ResultSet resultSet = pstmt.executeQuery()) {
                if (resultSet.next()) {
                    return new BookDetails(
                            resultSet.getString("title"),
                            resultSet.getString("author"),
                            resultSet.getString("isbn"),
                            resultSet.getString("publisher"),
                            resultSet.getInt("publicationYear"),
                            resultSet.getInt("edition"),
                            resultSet.getDouble("price"),
                            resultSet.getBoolean("availability"),
                            resultSet.getString("imagePath"),
                            resultSet.getString("description")

                    );
                }
            }
        }
        return null;
    }



    public static List<BookDetails> fetchAllBooks() {
        List<BookDetails> books = new ArrayList<>();
        String sql = "SELECT * FROM BookDetails";
        try (Connection conn = getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql);
             ResultSet resultSet = pstmt.executeQuery()) {
            while (resultSet.next()) {
                books.add(new BookDetails(
                        resultSet.getString("title"),
                        resultSet.getString("author"),
                        resultSet.getString("isbn"),
                        resultSet.getString("publisher"),
                        resultSet.getInt("publicationYear"),
                        resultSet.getInt("edition"),
                        resultSet.getDouble("price"),
                        resultSet.getBoolean("availability"),
                        resultSet.getString("imagePath"),
                        resultSet.getString("description")
                ));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return books;
    }


    public static boolean validateUser(String userId, String password, boolean isUser) {

        String sql;
        if (isUser) {
            // For regular users with plain text passwords
            sql = "SELECT password FROM Users WHERE userId = ?";
        } else {
            // For admins with hashed passwords
            sql = "SELECT hashed_password FROM Admins WHERE username = ?";
        }

        try (Connection conn = getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setString(1, userId);
            ResultSet rs = pstmt.executeQuery();
            if (rs.next()) {
                if (isUser) {
                    return rs.getString("password").equals(password);
                } else {
                    return rs.getString("hashed_password").equals(password);
                }
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return false;
    }


    public static String hashPassword(String password) {
        try {
            MessageDigest digest = MessageDigest.getInstance("SHA-256");
            byte[] encodedhash = digest.digest(password.getBytes());
            return HexFormat.of().formatHex(encodedhash);
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
            return null;
        }
    }
    public static void addAdmin(String username, String password) {
        String hashedPassword = hashPassword(password);

        String sql = "INSERT INTO Admins (username, hashed_password) VALUES (?, ?)";
        try (Connection conn = getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setString(1, username);
            pstmt.setString(2, hashedPassword);
            pstmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }


}

